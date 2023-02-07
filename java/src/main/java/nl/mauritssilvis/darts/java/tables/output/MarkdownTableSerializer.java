/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.tables.output;

import nl.mauritssilvis.darts.java.boards.Field;
import nl.mauritssilvis.darts.java.checkouts.Checkout;
import nl.mauritssilvis.darts.java.checkouts.Throw;
import nl.mauritssilvis.darts.java.output.Serializer;
import nl.mauritssilvis.darts.java.tables.Table;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * An implementation of the generic {@code Serializer} interface that serializes
 * {@code Table} objects to Markdown.
 * <p>
 * Relevant design patterns: strategy, immutable object, static factory method.
 */
public final class MarkdownTableSerializer implements Serializer<Table> {
    private MarkdownTableSerializer() {
    }

    /**
     * Returns a new {@code MarkdownTableSerializer}.
     *
     * @return a new {@code MarkdownTableSerializer}
     */
    public static Serializer<Table> create() {
        return new MarkdownTableSerializer();
    }

    @Override
    public String serialize(Table object) {
        Map<Integer, List<Checkout>> checkoutMap = object.getCheckoutMap();

        return new Printer(checkoutMap).print();
    }

    private static class Printer {
        private final Map<Integer, List<Checkout>> checkoutMap;
        private final Map<Integer, Long> multiplicityMap;
        private final int scoreWidth;
        private final int multiplicityWidth;
        private final int numThrows;
        private final int throwSize;
        private final int nameWidth;
        private final int columnWidth;
        private final String scoreFormat;
        private final String multiplicityFormat;
        private final String columnFormat;
        private final String nameFormat;

        Printer(Map<Integer, List<Checkout>> checkoutMap) {
            this.checkoutMap = checkoutMap;
            multiplicityMap = getMultiplicityMap(checkoutMap);

            Collection<Checkout> checkouts = checkoutMap.values().stream()
                    .flatMap(Collection::stream)
                    .toList();

            scoreWidth = getScoreWidth(checkoutMap.keySet());
            multiplicityWidth = getMultiplicityWidth(multiplicityMap.values());

            numThrows = getMaxNumThrows(checkouts);
            throwSize = getMaxThrowSize(checkouts);
            nameWidth = getNameWidth(checkouts);
            columnWidth = nameWidth * throwSize + throwSize - 1;

            scoreFormat = "| %1$" + scoreWidth + "s ";
            multiplicityFormat = "| %1$" + multiplicityWidth + "s ";
            columnFormat = "| %1$" + columnWidth + "s ";
            nameFormat = "%1$" + nameWidth + "s";
        }

        String print() {
            StringBuilder stringBuilder = new StringBuilder();

            writeHeader(stringBuilder);
            writeScores(stringBuilder);

            return stringBuilder.toString();
        }

        private static Map<Integer, Long> getMultiplicityMap(
                Map<Integer, ? extends Collection<? extends Checkout>> checkoutMap
        ) {
            return checkoutMap.entrySet().stream()
                    .collect(
                            Collectors.toMap(
                                    Map.Entry::getKey,
                                    e -> e.getValue().stream()
                                            .mapToLong(Checkout::getMultiplicity)
                                            .sum()
                            )
                    );
        }

        private static int getScoreWidth(Collection<Integer> scores) {
            int scoreWidth = scores.stream()
                    .map(String::valueOf)
                    .mapToInt(String::length)
                    .max()
                    .orElse(0);

            return Math.max(scoreWidth, "Score".length());
        }

        private static int getMultiplicityWidth(Collection<Long> multiplicities) {
            return multiplicities.stream()
                    .map(String::valueOf)
                    .mapToInt(String::length)
                    .max()
                    .orElse(0);
        }

        private static int getMaxNumThrows(Collection<? extends Checkout> checkouts) {
            return checkouts.stream()
                    .map(Checkout::getThrows)
                    .mapToInt(List::size)
                    .max()
                    .orElse(0);
        }

        private static int getMaxThrowSize(Collection<? extends Checkout> checkouts) {
            return checkouts.stream()
                    .map(Checkout::getThrows)
                    .flatMap(Collection::stream)
                    .map(Throw::getFields)
                    .mapToInt(List::size)
                    .max()
                    .orElse(0);
        }

        private static int getNameWidth(Collection<? extends Checkout> checkouts) {
            return checkouts.stream()
                    .map(Checkout::getThrows)
                    .flatMap(Collection::stream)
                    .map(Throw::getFields)
                    .flatMap(Collection::stream)
                    .map(Field::getName)
                    .mapToInt(String::length)
                    .max()
                    .orElse(0);
        }

        private void writeHeader(StringBuilder stringBuilder) {
            stringBuilder.append(String.format(scoreFormat, "Score"));

            IntStream.range(0, numThrows)
                    .mapToObj(i -> String.format(columnFormat, i + 1))
                    .forEach(stringBuilder::append);

            stringBuilder.append(String.format(multiplicityFormat, "#"))
                    .append("|\n")
                    .append("|-");

            IntStream.range(0, scoreWidth)
                    .mapToObj(i -> "-")
                    .forEach(stringBuilder::append);

            stringBuilder.append(":");

            IntStream.range(0, numThrows)
                    .mapToObj(i ->
                            "|-" + IntStream.range(0, columnWidth)
                                    .mapToObj(j -> "-")
                                    .collect(Collectors.joining()) + ":"
                    )
                    .forEach(stringBuilder::append);

            stringBuilder.append("|-");

            IntStream.range(0, multiplicityWidth)
                    .mapToObj(i -> "-")
                    .forEach(stringBuilder::append);

            stringBuilder.append(":|\n");
        }

        private void writeScores(StringBuilder stringBuilder) {
            checkoutMap.keySet().forEach(
                    k -> {
                        writeSubHeader(stringBuilder, k);
                        writeCheckouts(stringBuilder, k);
                    }
            );
        }

        private void writeSubHeader(StringBuilder stringBuilder, int score) {
            stringBuilder.append(String.format(scoreFormat, score));

            IntStream.range(0, numThrows)
                    .mapToObj(i ->
                            "|" + IntStream.range(0, columnWidth)
                                    .mapToObj(j -> " ")
                                    .collect(Collectors.joining()) + "- "
                    )
                    .forEach(stringBuilder::append);

            stringBuilder.append(String.format(multiplicityFormat, multiplicityMap.get(score)))
                    .append("|\n");
        }

        private void writeCheckouts(StringBuilder stringBuilder, int score) {
            List<Checkout> checkouts = checkoutMap.get(score);

            checkouts.forEach(checkout -> writeCheckout(stringBuilder, checkout));
        }

        private void writeCheckout(StringBuilder stringBuilder, Checkout checkout) {
            stringBuilder.append("|");

            IntStream.range(0, scoreWidth + 2)
                    .mapToObj(i -> " ")
                    .forEach(stringBuilder::append);

            Collection<Throw> throwCollection = checkout.getThrows();

            IntStream.range(0, numThrows - throwCollection.size())
                    .forEach(i -> writeEmptyThrow(stringBuilder));

            throwCollection.forEach(t -> writeThrow(stringBuilder, t));

            stringBuilder.append(String.format(multiplicityFormat, checkout.getMultiplicity()))
                    .append("|\n");
        }

        private void writeEmptyThrow(StringBuilder stringBuilder) {
            stringBuilder.append(String.format(columnFormat, "-"));
        }

        private void writeThrow(StringBuilder stringBuilder, Throw compoundThrow) {
            String fieldNames = compoundThrow.getFields().stream()
                    .map(Field::getName)
                    .map(name -> String.format(nameFormat, name))
                    .collect(Collectors.joining("/"));

            stringBuilder.append(String.format(columnFormat, fieldNames));
        }
    }
}
