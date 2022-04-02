package com.liga.store.service;

import com.liga.store.domain.Good;
import com.liga.store.service.Impl.GoodServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

/**
 * The service layer interface describes a set of methods for working with objects of the {@link Good} class.
 *
 * @author Nazirov Ilhomnjon (naziroffjr@gmail.com)
 * @version 1.0
 * @see Good
 * @see GoodServiceImpl
 */
public interface GoodService {
    /**
     * Return list of all goods.
     *
     * @return list of {@link Good}.
     */
    List<Good> findAll();

    /**
     * Returns list of goods in which the price is in the range between of starting price and ending price.
     * A {@link List} is a sublist of a list of objects.
     *
     * @param startingPrice The starting price of the product that the user enters.
     * @param endingPrice   The ending price of the product that the user enters.
     * @return list of {@link Good}.
     */
    List<Good> findByPriceBetween(Integer startingPrice, Integer endingPrice);

    /**
     * Returns list of goods which has the same good manufacturer with the value of the input parameter.
     * A {@link List} is a sublist of a list of objects.
     *
     * @param producer good manufacturer to return.
     * @return list of {@link Good}.
     */
    List<Good> findByProducer(String producer);




    /**
     * Returns list of goods which has the same good manufacturer or good title
     * with the value of the input parameter.
     * A {@link List} is a sublist of a list of objects.
     *
     * @param producer      good manufacturer to return.
     * @param goodTitle  good title to return.
     * @return list of {@link Good}.
     */
    List<Good> findByGoodOrGoodTitle(String producer, String goodTitle);


    /**
     * Returns minimum price of good.
     *
     * @return minimum price {@link Good}.
     */
    BigDecimal minGoodPrice();

    /**
     * Returns maximum price of good from the database.
     *
     * @return maximum price {@link Good}.
     */
    BigDecimal maxGoodPrice();

    /**
     * Save updated good.
     *
     * @param goodTitle          good title to update.
     * @param producer              good manufacturer to update.
     * @param year                  the year the good was released to update.
     * @param country               manufacturer country to update.
     * @param description           good description to update.
     * @param price                 good price to update.
     * @param type                  type of good to update.
     * @param id                    the unique code of the good to update.
     */
    void saveProductInfoById(String goodTitle, String producer, Integer year, String country,
                             String description, Integer price, String type, Long id);

    /**
     * Save good info.
     *
     * @param good good object to return.
     * @return The {@link Good} class object which will be saved in the database.
     */
    Good save(Good good);
}