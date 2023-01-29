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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

class GroupedCheckoutTests {
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
    void countTheThrows() {
        Collection<Collection<String>> namesPerThrow = List.of(List.of("D10", "20"), List.of("D9"));
        List<Throw> throwList = getThrows(namesPerThrow);

        Collection<Boolean> grouping = List.of(false, false);

        Checkout checkout = GroupedCheckout.of(throwList, grouping);

        Assertions.assertEquals(throwList.size(), checkout.countThrows());
    }

    @Test
    void countTheThrowsWithSingletonInput() {
        Collection<Collection<String>> namesPerThrow = List.of(List.of("Q4", "D8"));
        List<Throw> throwList = getThrows(namesPerThrow);

        Collection<Boolean> grouping = Collections.singletonList(false);

        Checkout checkout = GroupedCheckout.of(throwList, grouping);

        Assertions.assertEquals(1, checkout.countThrows());
    }

    @Test
    void countTheThrowsWithEmptyInput() {
        Collection<Throw> throwList = Collections.emptyList();
        Collection<Boolean> grouping = Collections.emptyList();
        Checkout checkout = GroupedCheckout.of(throwList, grouping);

        Assertions.assertEquals(0, checkout.countThrows());
    }

    @Test
    void countTheThrowsWithAShorterGroupingSignature() {
        Collection<Collection<String>> namesPerThrow = List.of(List.of("15"), List.of("D9", "18"));
        List<Throw> throwList = getThrows(namesPerThrow);

        Collection<Boolean> grouping = Collections.emptyList();

        Checkout checkout = GroupedCheckout.of(throwList, grouping);

        Assertions.assertEquals(throwList.size(), checkout.countThrows());
    }

    @Test
    void countTheThrowsWithALongerGroupingSignature() {
        Collection<Collection<String>> namesPerThrow = List.of(List.of("9"), List.of("T3", "9"));
        List<Throw> throwList = getThrows(namesPerThrow);

        Collection<Boolean> grouping = List.of(true, false, false);

        Checkout checkout = GroupedCheckout.of(throwList, grouping);

        Assertions.assertEquals(throwList.size(), checkout.countThrows());
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
    void storeIndependentThrows() {
        Collection<Collection<String>> namesPerThrow = List.of(List.of("1"), List.of("2", "D1"), List.of("3"));
        List<Throw> throwList = new ArrayList<>(getThrows(namesPerThrow));

        Collection<Boolean> grouping = List.of(false, false, false);

        Checkout checkout = GroupedCheckout.of(throwList, grouping);

        throwList.remove(2);

        Assertions.assertNotEquals(throwList, checkout.getThrows());
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

    // TODO: Multiplicity tests

    private static List<Throw> getThrows(Collection<? extends Collection<String>> namesPerThrow) {
        List<List<Field>> fieldsPerThrow = TypedFieldTestUtils.getFieldsPerThrow(namesPerThrow);

        return fieldsPerThrow.stream()
                .map(CompoundThrow::of)
                .toList();
    }
}
