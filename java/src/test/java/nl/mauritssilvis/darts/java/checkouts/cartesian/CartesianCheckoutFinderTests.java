/*
 * Copyright © 2023 Maurits H. Silvis
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package nl.mauritssilvis.darts.java.checkouts.cartesian;

import nl.mauritssilvis.darts.java.boards.Field;
import nl.mauritssilvis.darts.java.checkouts.Checkout;
import nl.mauritssilvis.darts.java.checkouts.CheckoutFinder;
import nl.mauritssilvis.darts.java.checkouts.utils.CheckoutTestUtils;
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

class CartesianCheckoutFinderTests {
    private static final Collection<String> SINGLES = List.of("1", "2", "3", "5");
    private static final Collection<String> DOUBLES = List.of("D1", "D2", "D3", "D5");
    private static final Collection<String> TRIPLES = List.of("T1", "T2", "T3");

    private static final Collection<String> SINGLES_DOUBLES = Stream.concat(SINGLES.stream(), DOUBLES.stream()).toList();
    private static final Collection<String> ANY = Stream.concat(TRIPLES.stream(), SINGLES_DOUBLES.stream()).toList();

    @Test
    void storeIndependentFieldsPerThrow() {
        Collection<Collection<String>> namesPerThrow = new ArrayList<>(
                List.of(List.of("D2", "D4"), List.of("D4", "D6"))
        );

        List<List<Field>> fieldsPerThrow = TypedFieldTestUtils.getFieldsPerThrow(namesPerThrow);

        CheckoutFinder checkoutFinder = CartesianCheckoutFinder.of(fieldsPerThrow);
        int score = 16;

        long totalMultiplicity = CheckoutTestUtils.getTotalMultiplicity(checkoutFinder.find(score));

        namesPerThrow.clear();

        long newTotalMultiplicity = CheckoutTestUtils.getTotalMultiplicity(checkoutFinder.find(score));

        Assertions.assertEquals(totalMultiplicity, newTotalMultiplicity);
    }

    @Test
    void storeIndependentCopiesOfTheFieldsPerThrow() {
        List<Collection<String>> namesPerThrow = new ArrayList<>(
                List.of(List.of("T1", "T9"), List.of("T9", "1"))
        );

        List<List<Field>> fieldsPerThrow = TypedFieldTestUtils.getFieldsPerThrow(namesPerThrow);

        CheckoutFinder checkoutFinder = CartesianCheckoutFinder.of(fieldsPerThrow);
        int score = 30;

        long totalMultiplicity = CheckoutTestUtils.getTotalMultiplicity(checkoutFinder.find(score));

        namesPerThrow.set(1, Collections.emptyList());

        long newTotalMultiplicity = CheckoutTestUtils.getTotalMultiplicity(checkoutFinder.find(score));

        Assertions.assertEquals(totalMultiplicity, newTotalMultiplicity);
    }

    @ParameterizedTest
    @MethodSource("withEmptyFieldsPerThrow")
    void handleEmptyFieldsPerThrow(Collection<? extends Collection<String>> namesPerThrow) {
        List<List<Field>> fieldsPerThrow = TypedFieldTestUtils.getFieldsPerThrow(namesPerThrow);
        CheckoutFinder checkoutFinder = CartesianCheckoutFinder.of(fieldsPerThrow);

        int score = 3;

        Collection<Checkout> checkouts = checkoutFinder.find(score);

        Assertions.assertEquals(0, checkouts.size());
    }

    @ParameterizedTest
    @MethodSource("withoutCheckouts")
    void doNotFindCheckouts(Collection<? extends Collection<String>> namesPerThrow, int score) {
        List<List<Field>> fieldsPerThrow = TypedFieldTestUtils.getFieldsPerThrow(namesPerThrow);
        CheckoutFinder checkoutFinder = CartesianCheckoutFinder.of(fieldsPerThrow);

        Collection<Checkout> checkouts = checkoutFinder.find(score);

        Assertions.assertEquals(0, checkouts.size());
    }

    @ParameterizedTest
    @MethodSource("withCheckouts")
    void findCheckouts(
            Collection<? extends Collection<String>> namesPerThrow,
            int score,
            Collection<Collection<? extends Collection<String>>> namesPerCheckout
    ) {
        List<List<Field>> fieldsPerThrow = TypedFieldTestUtils.getFieldsPerThrow(namesPerThrow);
        CheckoutFinder checkoutFinder = CartesianCheckoutFinder.of(fieldsPerThrow);

        List<Checkout> checkouts = checkoutFinder.find(score);

        Assertions.assertAll(
                () -> Assertions.assertEquals(score, checkouts.get(0).getScore()),
                () -> Assertions.assertEquals(score, CheckoutTestUtils.getTotalScore(checkouts) / checkouts.size()),
                () -> Assertions.assertEquals(namesPerThrow.size(), checkouts.get(0).countThrows()),
                () -> Assertions.assertEquals(namesPerThrow.size(), CheckoutTestUtils.countTotalThrows(checkouts) / checkouts.size()),
                () -> Assertions.assertEquals(namesPerCheckout.size(), checkouts.size()),
                () -> Assertions.assertEquals(namesPerCheckout, CheckoutTestUtils.getNamesPerCheckout(checkouts)),
                () -> Assertions.assertEquals(namesPerCheckout.size(), CheckoutTestUtils.getTotalMultiplicity(checkouts))
        );
    }

    private static Stream<Arguments> withEmptyFieldsPerThrow() {
        return Stream.of(
                Arguments.of(Collections.emptyList()),
                Arguments.of(List.of(Collections.emptyList())),
                Arguments.of(List.of(Collections.emptyList(), Collections.emptyList())),
                Arguments.of(List.of(Collections.emptyList(), List.of("D3"))),
                Arguments.of(List.of(List.of("T8", "D9"), Collections.emptyList())),
                Arguments.of(List.of(List.of("Q2"), Collections.emptyList(), List.of("25")))
        );
    }

    private static Stream<Arguments> withoutCheckouts() {
        return Stream.of(
                Arguments.of(
                        List.of(List.of("1")),
                        0
                ),
                Arguments.of(
                        List.of(List.of("1")),
                        2
                ),
                Arguments.of(
                        List.of(List.of("D1")),
                        0
                ),
                Arguments.of(
                        List.of(List.of("D1")),
                        1
                ),
                Arguments.of(
                        List.of(List.of("1", "2")),
                        0
                ),
                Arguments.of(
                        List.of(List.of("1", "D2")),
                        5
                ),
                Arguments.of(
                        List.of(List.of("D1", "D1")),
                        4
                ),
                Arguments.of(
                        List.of(List.of("D1", "D2")),
                        3
                ),
                Arguments.of(
                        List.of(List.of("D1", "D2")),
                        5
                ),
                Arguments.of(
                        List.of(List.of("1", "2", "1")),
                        3
                ),
                Arguments.of(
                        List.of(ANY, ANY, ANY),
                        2
                )
        );
    }

    private static Stream<Arguments> withCheckouts() {
        return Stream.of(
                Arguments.of(
                        List.of(List.of("1")),
                        1,
                        List.of(List.of(List.of("1")))
                ),
                Arguments.of(
                        List.of(List.of("D1")),
                        2,
                        List.of(List.of(List.of("D1")))
                ),
                Arguments.of(
                        List.of(List.of("1", "1")),
                        1,
                        List.of(List.of(List.of("1")))
                ),
                Arguments.of(
                        List.of(List.of("1", "2")),
                        1,
                        List.of(List.of(List.of("1")))
                ),
                Arguments.of(
                        List.of(List.of("D1", "D1")),
                        2,
                        List.of(List.of(List.of("D1")))
                ),
                Arguments.of(
                        List.of(List.of("D1", "D2")),
                        2,
                        List.of(List.of(List.of("D1")))
                ),
                Arguments.of(
                        List.of(List.of("D1", "D2")),
                        4,
                        List.of(List.of(List.of("D2")))
                ),
                Arguments.of(
                        List.of(List.of("1", "2", "1")),
                        2,
                        List.of(List.of(List.of("2")))
                ),
                Arguments.of(
                        List.of(List.of("D2", "D3", "T2", "T3")),
                        4,
                        List.of(List.of(List.of("D2")))
                ),
                Arguments.of(
                        List.of(List.of("D2", "D3", "T2", "T3")),
                        9,
                        List.of(List.of(List.of("T3")))
                ),
                Arguments.of(
                        List.of(List.of("D2", "D3", "T2", "T3")),
                        6,
                        List.of(
                                List.of(List.of("D3")),
                                List.of(List.of("T2"))
                        )
                ),
                Arguments.of(
                        List.of(List.of("T2", "T3", "D2", "D3", "T2")),
                        6,
                        List.of(
                                List.of(List.of("T2")),
                                List.of(List.of("D3"))
                        )
                ),
                Arguments.of(
                        List.of(List.of("2", "6", "D2", "D3", "T2", "T3")),
                        6,
                        List.of(
                                List.of(List.of("6")),
                                List.of(List.of("D3")),
                                List.of(List.of("T2"))
                        )
                ),
                Arguments.of(
                        List.of(
                                List.of("1"),
                                List.of("2")
                        ),
                        3,
                        List.of(List.of(List.of("1"), List.of("2")))
                ),
                Arguments.of(
                        List.of(
                                List.of("D4"),
                                List.of("D4")
                        ),
                        16,
                        List.of(List.of(List.of("D4"), List.of("D4")))
                ),
                Arguments.of(
                        List.of(
                                List.of("1", "D1"),
                                List.of("D1", "1")
                        ),
                        3,
                        List.of(
                                List.of(List.of("1"), List.of("D1")),
                                List.of(List.of("D1"), List.of("1"))
                        )
                ),
                Arguments.of(
                        List.of(
                                List.of("D5", "10"),
                                List.of("D5", "10")
                        ),
                        20,
                        List.of(
                                List.of(List.of("D5"), List.of("D5")),
                                List.of(List.of("D5"), List.of("10")),
                                List.of(List.of("10"), List.of("D5")),
                                List.of(List.of("10"), List.of("10"))
                        )
                ),
                Arguments.of(
                        List.of(
                                List.of("1", "D2", "D3"),
                                List.of("D3", "1")
                        ),
                        7,
                        List.of(
                                List.of(List.of("1"), List.of("D3")),
                                List.of(List.of("D3"), List.of("1"))
                        )
                ),
                Arguments.of(
                        List.of(
                                List.of("D3", "D7", "D5"),
                                List.of("D11", "D9")
                        ),
                        28,
                        List.of(
                                List.of(List.of("D3"), List.of("D11")),
                                List.of(List.of("D5"), List.of("D9"))
                        )
                ),
                Arguments.of(
                        List.of(
                                List.of("D3", "D5", "6"),
                                List.of("D11", "D9")
                        ),
                        28,
                        List.of(
                                List.of(List.of("D3"), List.of("D11")),
                                List.of(List.of("6"), List.of("D11")),
                                List.of(List.of("D5"), List.of("D9"))
                        )
                ),
                Arguments.of(
                        List.of(
                                List.of("T1"),
                                List.of("T5"),
                                List.of("T9")
                        ),
                        45,
                        List.of(List.of(List.of("T1"), List.of("T5"), List.of("T9")))
                ),
                Arguments.of(
                        List.of(
                                List.of("Q2"),
                                List.of("Q6"),
                                List.of("Q6")
                        ),
                        56,
                        List.of(List.of(List.of("Q2"), List.of("Q6"), List.of("Q6")))
                ),
                Arguments.of(
                        List.of(
                                List.of("18", "D18"),
                                List.of("18", "D18"),
                                List.of("18", "D18")
                        ),
                        54,
                        List.of(List.of(List.of("18"), List.of("18"), List.of("18")))
                ),
                Arguments.of(
                        List.of(
                                List.of("18", "D18"),
                                List.of("18", "D18"),
                                List.of("D18", "18")
                        ),
                        72,
                        List.of(
                                List.of(List.of("18"), List.of("18"), List.of("D18")),
                                List.of(List.of("18"), List.of("D18"), List.of("18")),
                                List.of(List.of("D18"), List.of("18"), List.of("18"))
                        )
                ),
                Arguments.of(
                        List.of(
                                List.of("D4", "8"),
                                List.of("8", "D4"),
                                List.of("8", "D4")
                        ),
                        24,
                        List.of(
                                List.of(List.of("D4"), List.of("8"), List.of("8")),
                                List.of(List.of("D4"), List.of("8"), List.of("D4")),
                                List.of(List.of("D4"), List.of("D4"), List.of("8")),
                                List.of(List.of("D4"), List.of("D4"), List.of("D4")),
                                List.of(List.of("8"), List.of("8"), List.of("8")),
                                List.of(List.of("8"), List.of("8"), List.of("D4")),
                                List.of(List.of("8"), List.of("D4"), List.of("8")),
                                List.of(List.of("8"), List.of("D4"), List.of("D4"))
                        )
                ),
                Arguments.of(
                        List.of(ANY, ANY, ANY),
                        3,
                        List.of(List.of(List.of("1"), List.of("1"), List.of("1")))
                ),
                Arguments.of(
                        List.of(ANY, ANY, ANY),
                        30,
                        List.of(List.of(List.of("D5"), List.of("D5"), List.of("D5")))
                ),
                Arguments.of(
                        List.of(ANY, ANY, ANY),
                        28,
                        List.of(
                                List.of(List.of("T3"), List.of("T3"), List.of("D5")),
                                List.of(List.of("T3"), List.of("D5"), List.of("T3")),
                                List.of(List.of("D5"), List.of("T3"), List.of("T3"))
                        )
                ),
                Arguments.of(
                        List.of(ANY, ANY, ANY),
                        29,
                        List.of(
                                List.of(List.of("T3"), List.of("D5"), List.of("D5")),
                                List.of(List.of("D5"), List.of("T3"), List.of("D5")),
                                List.of(List.of("D5"), List.of("D5"), List.of("T3"))
                        )
                ),
                Arguments.of(
                        List.of(ANY, ANY, ANY),
                        4,
                        List.of(
                                List.of(List.of("1"), List.of("1"), List.of("2")),
                                List.of(List.of("1"), List.of("1"), List.of("D1")),
                                List.of(List.of("1"), List.of("2"), List.of("1")),
                                List.of(List.of("1"), List.of("D1"), List.of("1")),
                                List.of(List.of("2"), List.of("1"), List.of("1")),
                                List.of(List.of("D1"), List.of("1"), List.of("1"))
                        )
                ),
                Arguments.of(
                        List.of(ANY, ANY, ANY, ANY),
                        40,
                        List.of(List.of(List.of("D5"), List.of("D5"), List.of("D5"), List.of("D5")))
                ),
                Arguments.of(
                        List.of(ANY, ANY, ANY, ANY),
                        39,
                        List.of(
                                List.of(List.of("T3"), List.of("D5"), List.of("D5"), List.of("D5")),
                                List.of(List.of("D5"), List.of("T3"), List.of("D5"), List.of("D5")),
                                List.of(List.of("D5"), List.of("D5"), List.of("T3"), List.of("D5")),
                                List.of(List.of("D5"), List.of("D5"), List.of("D5"), List.of("T3"))
                        )
                ),
                Arguments.of(
                        List.of(ANY, ANY, ANY, ANY),
                        38,
                        List.of(
                                List.of(List.of("T3"), List.of("T3"), List.of("D5"), List.of("D5")),
                                List.of(List.of("T3"), List.of("D5"), List.of("T3"), List.of("D5")),
                                List.of(List.of("T3"), List.of("D5"), List.of("D5"), List.of("T3")),
                                List.of(List.of("D5"), List.of("T3"), List.of("T3"), List.of("D5")),
                                List.of(List.of("D5"), List.of("T3"), List.of("D5"), List.of("T3")),
                                List.of(List.of("D5"), List.of("D5"), List.of("T3"), List.of("T3"))
                        )
                )
        );
    }
}
