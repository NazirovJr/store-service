package com.liga.store.service.Impl;

import com.liga.store.domain.Good;
import com.liga.store.repos.GoodRepository;
import com.liga.store.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * The service layer class implements the accessor methods of {@link Good} objects
 * in the {@link GoodService} interface database.
 * The class is marked with the @Service annotation - an annotation announcing that this class
 * is a service - a component of the service layer. Service is a subtype of @Component class.
 * Using this annotation will automatically search for service beans.
 *
 * @author Nazirov Ilhomnjon (naziroffjr@gmail.com)
 * @version 1.0
 * @see Good
 * @see GoodService
 * @see GoodRepository
 */
@Service
public class GoodServiceImpl implements GoodService {
    /**
     * Implementation of the {@link GoodRepository} interface
     * for working with perfumes with a database.
     */
    private final GoodRepository goodRepository;

    /**
     * Constructor for initializing the main variables of the order service.
     * The @Autowired annotation will allow Spring to automatically initialize objects.
     *
     * @param goodRepository implementation of the {@link GoodRepository} interface
     *                        for working with perfumes with a database.
     */
    @Autowired
    public GoodServiceImpl(GoodRepository goodRepository) {
        this.goodRepository = goodRepository;
    }

    /**
     * Return list of all goods.
     *
     * @return list of {@link Good}.
     */
    @Override
    public List<Good> findAll() {
        return goodRepository.findAll();
    }



    /**
     * Returns list of goods in which the price is in the range between of starting price and ending price.
     * A {@link List} is a sublist of a list of objects.
     *
     * @param startingPrice The starting price of the product that the user enters.
     * @param endingPrice   The ending price of the product that the user enters.
     * @return list of {@link Good}.
     */
    @Override
    public List<Good> findByPriceBetween(Integer startingPrice, Integer endingPrice) {
        return null;
    }

    /**
     * Returns list of perfumes which has the same good manufacturer with the value of the input parameter.
     * A {@link List} is a sublist of a list of objects.
     *
     * @param producer good manufacturer to return.
     * @return list of {@link Good}.
     */
    @Override
    public List<Good> findByProducer(String producer) {
        return null;
    }

    /**
     * Returns list of perfumes which has the same good manufacturer or good title
     * with the value of the input parameter.
     * A {@link List} is a sublist of a list of objects.
     *
     * @param producer      good manufacturer to return.
     * @param goodTitle  good title to return.
     * @return list of {@link Good}.
     */
    @Override
    public List<Good> findByGoodOrGoodTitle(String producer, String goodTitle) {
        return goodRepository.findByGoodOrGoodTitle(producer, goodTitle);
    }

    /**
     * Returns minimum price of good.
     *
     * @return minimum price {@link Good}.
     */
    @Override
    public BigDecimal minGoodPrice() {
        return goodRepository.minGoodPrice();
    }


    /**
     * Returns maximum price of good from the database.
     *
     * @return maximum price {@link Good}.
     */
    @Override
    public BigDecimal maxGoodPrice() {
        return goodRepository.maxGoodPrice();
    }

    /**
     * Save updated good.
     *
     * @param producer              good manufacturer to update.
     * @param year                  the year the good was released to update.
     * @param country               manufacturer country to update.
     * @param price                 good price to update.
     * @param type                  type of fragrance to update.
     * @param id                    the unique code of the good to update.
     */
    @Override
    public void saveProductInfoById(String goodTitle, String producer, Integer year, String country, String description, Integer price, String type, Long id) {

    }


    /**
     * Save good info.
     *
     * @param good good object to return.
     * @return The {@link Good} class object which will be saved in the database.
     */
    @Override
    public Good save(Good good) {
        return goodRepository.save(good);
    }
}
