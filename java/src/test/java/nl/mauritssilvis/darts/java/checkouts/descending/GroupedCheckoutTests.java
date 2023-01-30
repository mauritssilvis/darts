/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.checkouts.descending;

import nl.mauritssilvis.darts.java.boards.Field;
import nl.mauritssilvis.darts.java.checkouts.Checkout;
import nl.mauritssilvis.darts.java.checkouts.Throw;
import nl.mauritssilvis.darts.java.checkouts.utils.TypedFieldTestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

class GroupedCheckoutTests {
    @Test
    void storeIndependentThrows() {
        Collection<Collection<String>> namesPerThrow = List.of(List.of("1"), List.of("2", "D1"), List.of("3"));
        List<Throw> throwList = new ArrayList<>(getThrows(namesPerThrow));

        Collection<Boolean> grouping = List.of(false, false, false);

        Checkout checkout = GroupedCheckout.of(throwList, grouping);

        throwList.remove(2);

        Assertions.assertNotEquals(throwList, checkout.getThrows());
    }

    @Test
    void storeAnIndependentGrouping() {
        Collection<Collection<String>> namesPerThrow = List.of(List.of("1"), List.of("2"));
        Collection<Throw> throwList = new ArrayList<>(getThrows(namesPerThrow));

        List<Boolean> grouping = new ArrayList<>(List.of(false, false));

        Checkout checkout = GroupedCheckout.of(throwList, grouping);

        long multiplicity = checkout.getMultiplicity();

        grouping.set(1, true);

        long newMultiplicity = checkout.getMultiplicity();

        Assertions.assertEquals(multiplicity, newMultiplicity);
    }

    @Test
    void getTheScore() {
        Collection<Collection<String>> namesPerThrow = List.of(List.of("D2", "4"), List.of("2"), List.of("3"));
        List<Throw> throwList = getThrows(namesPerThrow);

        Collection<Boolean> grouping = List.of(false, true, false);

        Checkout checkout = GroupedCheckout.of(throwList, grouping);

        Assertions.assertEquals(9, checkout.getScore());
    }

    @Test
    void getTheScoreWithSingletonInput() {
        Collection<Collection<String>> namesPerThrow = List.of(List.of("D10"));
        Collection<Throw> throwList = getThrows(namesPerThrow);

        Collection<Boolean> grouping = Collections.singleton(false);

        Checkout checkout = GroupedCheckout.of(throwList, grouping);

        Assertions.assertEquals(20, checkout.getScore());
    }

    @Test
    void getTheScoreWithEmptyInput() {
        Collection<Throw> throwList = Collections.emptyList();
        Collection<Boolean> grouping = Collections.emptyList();
        Checkout checkout = GroupedCheckout.of(throwList, grouping);

        Assertions.assertEquals(0, checkout.getScore());
    }

    @Test
    void getTheScoreWithAShorterGroupingSignature() {
        Collection<Collection<String>> namesPerThrow = List.of(List.of("3"), List.of("T2", "D3", "6"));
        List<Throw> throwList = getThrows(namesPerThrow);

        Collection<Boolean> grouping = List.of(false);

        Checkout checkout = GroupedCheckout.of(throwList, grouping);

        Assertions.assertEquals(9, checkout.getScore());
    }

    @Test
    void getTheScoreWithALongerGroupingSignature() {
        Collection<Collection<String>> namesPerThrow = List.of(List.of("D25"));
        List<Throw> throwList = getThrows(namesPerThrow);

        Collection<Boolean> grouping = List.of(false, true, false);

        Checkout checkout = GroupedCheckout.of(throwList, grouping);

        Assertions.assertEquals(50, checkout.getScore());
    }

    @Test
    void getTheThrows() {
        Collection<Collection<String>> namesPerThrow = List.of(List.of("Q3", "T4"), List.of("8"));
        List<Throw> throwList = getThrows(namesPerThrow);

        Collection<Boolean> grouping = List.of(false, true);

        Checkout checkout = GroupedCheckout.of(throwList, grouping);

        Assertions.assertEquals(throwList, checkout.getThrows());
    }

    @Test
    void getTheThrowsWithSingletonInput() {
        Collection<Collection<String>> namesPerThrow = List.of(List.of("18"));
        List<Throw> throwList = getThrows(namesPerThrow);

        Collection<Boolean> grouping = Collections.singletonList(false);

        Checkout checkout = GroupedCheckout.of(throwList, grouping);

        Assertions.assertEquals(throwList, checkout.getThrows());
    }

    @Test
    void getTheThrowsWithEmptyInput() {
        Collection<Throw> throwList = Collections.emptyList();
        Collection<Boolean> grouping = Collections.emptyList();
        Checkout checkout = GroupedCheckout.of(throwList, grouping);

        Assertions.assertEquals(throwList, checkout.getThrows());
    }

    @Test
    void getTheThrowsWithAShorterGroupingSignature() {
        Collection<Collection<String>> namesPerThrow = List.of(List.of("6"), List.of("8"));
        List<Throw> throwList = getThrows(namesPerThrow);

        Collection<Boolean> grouping = Collections.emptyList();

        Checkout checkout = GroupedCheckout.of(throwList, grouping);

        Assertions.assertEquals(throwList, checkout.getThrows());
    }

    @Test
    void getTheThrowsWithALongerGroupingSignature() {
        Collection<Collection<String>> namesPerThrow = Collections.emptyList();
        List<Throw> throwList = getThrows(namesPerThrow);

        Collection<Boolean> grouping = List.of(false);

        Checkout checkout = GroupedCheckout.of(throwList, grouping);

        Assertions.assertEquals(throwList, checkout.getThrows());
    }

    @Test
    void getImmutableThrows() {
        Collection<Collection<String>> namesPerThrow = List.of(List.of("12", "T4", "D6", "Q3"));
        List<Throw> throwList = getThrows(namesPerThrow);

        Collection<Boolean> grouping = List.of(false);

        Checkout checkout = GroupedCheckout.of(throwList, grouping);

        List<Throw> storedThrows = checkout.getThrows();

        Assertions.assertThrows(UnsupportedOperationException.class, () -> storedThrows.remove(2));
    }

    @ParameterizedTest
    @MethodSource("withMultiplicityData")
    void getTheMultiplicity(
            Collection<? extends Collection<String>> namesPerThrow,
            Collection<Boolean> grouping,
            int multiplicity
    ) {
        List<Throw> throwList = getThrows(namesPerThrow);

        Checkout checkout = GroupedCheckout.of(throwList, grouping);

        Assertions.assertEquals(multiplicity, checkout.getMultiplicity());
    }

    private static List<Throw> getThrows(Collection<? extends Collection<String>> namesPerThrow) {
        List<List<Field>> fieldsPerThrow = TypedFieldTestUtils.getFieldsPerThrow(namesPerThrow);

        return fieldsPerThrow.stream()
                .map(CompoundThrow::of)
                .toList();
    }

    private static Stream<Arguments> withMultiplicityData() {
        return Stream.of(
                // Empty throws
                Arguments.of(
                        Collections.emptyList(), Collections.emptyList(), 0
                ),
                Arguments.of(
                        List.of(Collections.emptyList()), List.of(false), 0
                ),
                Arguments.of(
                        List.of(Collections.emptyList(), List.of("1")), List.of(false, true), 0
                ),
                // Ungrouped, simple throws
                Arguments.of(
                        List.of(List.of("1")), List.of(false), 1
                ),
                Arguments.of(
                        List.of(List.of("1"), List.of("2")), List.of(false, false, true), 1
                ),
                Arguments.of(
                        List.of(List.of("1", "1"), List.of("1")), List.of(false, false), 1
                ),
                Arguments.of(
                        List.of(List.of("1"), List.of("2"), List.of("3")), List.of(false, false), 1
                ),
                Arguments.of(
                        List.of(
                                List.of("T20"),
                                List.of("T20"),
                                List.of("T20"),
                                List.of("T20"),
                                List.of("T20"),
                                List.of("T20"),
                                List.of("T20"),
                                List.of("T19"),
                                List.of("D12")
                        ),
                        List.of(false, true, true, true, true, true, true, true, false),
                        8
                ),
                Arguments.of(
                        List.of(
                                List.of("T20"),
                                List.of("T20"),
                                List.of("T20"),
                                List.of("T20"),
                                List.of("T20"),
                                List.of("T20"),
                                List.of("T19"),
                                List.of("T18"),
                                List.of("D15")
                        ),
                        List.of(false, true, true, true, true, true, true, true, false),
                        56
                ),
                Arguments.of(
                        List.of(
                                List.of("T20"),
                                List.of("T20"),
                                List.of("T20"),
                                List.of("T20"),
                                List.of("T20"),
                                List.of("T19"),
                                List.of("T19"),
                                List.of("T19"),
                                List.of("D15")
                        ),
                        List.of(false, true, true, true, true, true, true, true, false),
                        56
                ),
                Arguments.of(
                        List.of(
                                List.of("T20"),
                                List.of("T20"),
                                List.of("T20"),
                                List.of("T20"),
                                List.of("T20"),
                                List.of("T19"),
                                List.of("T19"),
                                List.of("T17"),
                                List.of("D18")
                        ),
                        List.of(false, true, true, true, true, true, true, true, false),
                        168
                ),
                Arguments.of(
                        List.of(
                                List.of("T20"),
                                List.of("T20"),
                                List.of("T20"),
                                List.of("T20"),
                                List.of("T19"),
                                List.of("T19"),
                                List.of("T19"),
                                List.of("T18"),
                                List.of("D18")
                        ),
                        List.of(false, true, true, true, true, true, true, true, false),
                        280
                ),
                Arguments.of(
                        List.of(
                                List.of("T20"),
                                List.of("T20"),
                                List.of("T20"),
                                List.of("T20"),
                                List.of("T20"),
                                List.of("T19"),
                                List.of("T18"),
                                List.of("D25"),
                                List.of("D20")
                        ),
                        List.of(false, true, true, true, true, true, true, true, false),
                        336
                ),
                Arguments.of(
                        List.of(
                                List.of("T20"),
                                List.of("T20"),
                                List.of("T20"),
                                List.of("T20"),
                                List.of("T19"),
                                List.of("T18"),
                                List.of("D25"),
                                List.of("D25"),
                                List.of("D25")
                        ),
                        List.of(false, true, true, true, true, true, true, true, false),
                        840
                ),
                Arguments.of(
                        List.of(
                                List.of("T20"),
                                List.of("T20"),
                                List.of("T20"),
                                List.of("T19"),
                                List.of("T19"),
                                List.of("T19"),
                                List.of("D25"),
                                List.of("D25"),
                                List.of("D25")
                        ),
                        List.of(false, true, true, true, true, true, true, true, false),
                        560
                ),
                // Ungrouped, compound throws
                Arguments.of(
                        List.of(List.of("D7", "14")), List.of(false), 2
                ),
                Arguments.of(
                        List.of(List.of("12", "D6", "T4")), List.of(false), 3
                ),
                Arguments.of(
                        List.of(List.of("12", "D6", "T4", "Q3", "D6")), List.of(false, true), 4
                ),
                Arguments.of(
                        List.of(List.of("D10", "20"), List.of("T3", "9")), List.of(false, false), 4
                ),
                Arguments.of(
                        List.of(List.of("15", "T5", "T5"), List.of("15", "T5")), List.of(false), 4
                ),
                Arguments.of(
                        List.of(List.of("10", "D5"), List.of("T19", "T19")), List.of(false, false, true), 2
                ),
                Arguments.of(
                        List.of(List.of("18"), List.of("D9", "18")), List.of(false, false), 2
                ),
                Arguments.of(
                        List.of(List.of("3", "3"), List.of("T3", "9")), List.of(false), 2
                ),
                Arguments.of(
                        List.of(List.of("Q2", "8", "D4"), List.of("6", "T2", "D3")), List.of(false, false), 9
                ),
                Arguments.of(
                        List.of(List.of("11"), List.of("6", "T2", "T2", "D3")), List.of(false, false), 3
                ),
                Arguments.of(
                        List.of(List.of("2", "D1"), List.of("2"), List.of("3")), List.of(false, false, false), 2
                ),
                // Partly grouped, simple throws
                Arguments.of(
                        List.of(List.of("4"), List.of("5"), List.of("4")), List.of(false, true, false), 2
                ),
                Arguments.of(
                        List.of(List.of("4"), List.of("4"), List.of("6")), List.of(false, true), 1
                ),
                Arguments.of(
                        List.of(List.of("4"), List.of("5"), List.of("6"), List.of("7")),
                        List.of(false, true, false, true),
                        4
                ),
                Arguments.of(
                        List.of(List.of("4"), List.of("5"), List.of("8"), List.of("8")),
                        List.of(false, true, false, true),
                        2
                ),
                Arguments.of(
                        List.of(List.of("3"), List.of("3"), List.of("9"), List.of("9")),
                        List.of(false, true, false, true),
                        1
                ),
                // Partly grouped, compound throws
                Arguments.of(
                        List.of(List.of("2", "D1"), List.of("4", "D2"), List.of("18", "T6")),
                        List.of(false, true, false),
                        16
                ),
                Arguments.of(
                        List.of(List.of("2", "D1"), List.of("4", "D2"), List.of("7")), List.of(false, true, false), 8
                ),
                Arguments.of(
                        List.of(List.of("8", "Q2"), List.of("8"), List.of("6", "D3")),
                        List.of(false, true, false, true),
                        6
                ),
                Arguments.of(
                        List.of(List.of("18", "D9", "T6"), List.of("12", "T4", "Q3"), List.of("18")),
                        List.of(false, false, true),
                        18
                ),
                Arguments.of(
                        List.of(List.of("6", "D3", "T2"), List.of("6"), List.of("18", "T6")),
                        List.of(false, true),
                        10
                ),
                Arguments.of(
                        List.of(List.of("6", "D3"), List.of("18", "T6"), List.of("18", "T6")),
                        List.of(false, false, true),
                        8
                ),
                Arguments.of(
                        List.of(List.of("6", "D3"), List.of("6", "T2"), List.of("6", "T2", "D3", "D3")),
                        List.of(false, true),
                        21
                ),
                Arguments.of(
                        List.of(List.of("D1", "2"), List.of("2"), List.of("4", "D2"), List.of("8", "D4")),
                        List.of(false, false, true, false),
                        16
                ),
                // Fully grouped, simple throws
                Arguments.of(
                        List.of(List.of("5"), List.of("7")), List.of(false, true), 2
                ),
                Arguments.of(
                        List.of(List.of("2"), List.of("2")), List.of(false, true, false), 1
                ),
                Arguments.of(
                        List.of(List.of("5"), List.of("7"), List.of("9")), List.of(true, true, true), 6
                ),
                Arguments.of(
                        List.of(List.of("8"), List.of("6"), List.of("8")), List.of(false, true, true), 3
                ),
                Arguments.of(
                        List.of(List.of("9"), List.of("9"), List.of("9")), List.of(false, true, true, false), 1
                ),
                // Fully grouped, compound throws
                Arguments.of(
                        List.of(List.of("D10", "20"), List.of("D5", "10")), List.of(false, true), 8
                ),
                Arguments.of(
                        List.of(List.of("D5", "10"), List.of("D5", "10")), List.of(false, true), 4
                ),
                Arguments.of(
                        List.of(List.of("D5", "10"), List.of("D5")), List.of(false, true), 3
                ),
                Arguments.of(
                        List.of(List.of("8", "D4"), List.of("10", "D5"), List.of("12", "D6")),
                        List.of(false, true, true),
                        48
                ),
                Arguments.of(
                        List.of(List.of("4", "D2"), List.of("6", "D3"), List.of("10")), List.of(true, true, true), 24
                ),
                Arguments.of(
                        List.of(List.of("12", "D6"), List.of("12", "T4"), List.of("D6", "Q3")),
                        List.of(false, true, true, false),
                        36
                ),
                Arguments.of(
                        List.of(List.of("4", "D2"), List.of("4", "D2"), List.of("4", "D2")),
                        List.of(false, true, true),
                        8
                )
        );
    }
}
