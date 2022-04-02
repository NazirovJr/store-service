package com.liga.store.repos;

import com.liga.store.domain.Good;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * A repository for {@link Good} objects providing a set of JPA methods for working with the database.
 * Inherits interface {@link JpaRepository}.
 *
 * @author Nazirov Ilhomnjon (naziroffjr@gmail.com)
 * @version 1.0
 * @see Good
 * @see JpaRepository
 */
public interface GoodRepository extends JpaRepository<Good, Long> {
    /**
     * Returns list of goods from the database.
     * A {@link List} is a sublist of a list of objects.
     *
     * @return list of {@link Good}.
     */
    List<Good> findAll();

    /**
     * Returns list of goods from the database in which the price is in the range between of starting price and ending price.
     * A {@link List} is a sublist of a list of objects.
     *
     * @param startingPrice The starting price of the product that the user enters.
     * @param endingPrice   The ending price of the product that the user enters.
     * @return list of {@link Good}.
     */
    List<Good> findByPriceBetween(Integer startingPrice, Integer endingPrice);

    /**
     * Returns list of goods from the database which has the same good manufacturer with the value of the input parameter.
     * A {@link List} is a sublist of a list of objects.
     *
     * @param producer good manufacturer to return.
     * @return list of {@link Good}.
     */
    List<Good> findByProducer(String producer);




    /**
     * Returns list of goods from the database which has the same good manufacturer or good title
     * with the value of the input parameter.
     * A {@link List} is a sublist of a list of objects.
     *
     * @param producer      good manufacturer to return.
     * @param goodTitle  good title to return.
     * @return list of {@link Good}.
     */
    List<Good> findByGoodOrGoodTitle(String producer, String goodTitle);



    /**
     * Returns list of goods from the database which has the same good manufacturers
     * with the value of the input parameter.
     * A {@link List} is a sublist of a list of objects.
     *
     * @param producer good manufacturers to return.
     * @return list of {@link Good}.
     */
    List<Good> findByGoodIn (List<String> producer);

    /**
     * Returns minimum price of good from the database.
     * The @Query annotation to declare finder queries directly on repository methods.
     *
     * @return minimum price {@link Good}.
     */
    @Query(value = "SELECT min(price) FROM Good ")
    BigDecimal minGoodPrice();

    /**
     * Returns maximum price of good from the database.
     * The @Query annotation to declare finder queries directly on repository methods.
     *
     * @return maximum price {@link Good}.
     */
    @Query(value = "SELECT max(price) FROM Good ")
    BigDecimal maxGoodPrice();

    /**
     * Save updated good to the database.
     * The @Modifying annotation declaring manipulating queries.
     * The @Transactional annotation - before the execution of the method marked with this annotation,
     * a transaction starts, after the method is executed, the transaction is committed,
     * and when a RuntimeException is thrown, it is rolled back.
     * The @Query annotation to declare finder queries directly on repository methods.
     *
     * @param goodTitle          good title to update.
     * @param producer              good manufacturer to update.
     * @param year                  the year the good was released to update.
     * @param country               manufacturer country to update.
     * @param price                 good price to update.
     * @param type                  type of fragrance to update.
     * @param id                    the unique code of the good to update.
     */
    @Modifying
    @Transactional
    @Query("update Good p set p.perfumeTitle = ?1, p.perfumer = ?2, p.year = ?3, p.country = ?4, " +
            "p.perfumeGender = ?5, p.fragranceTopNotes = ?6, p.fragranceMiddleNotes = ?7, p.fragranceBaseNotes = ?8," +
            "p.description = ?9, p.filename = ?10, p.price = ?11, p.volume = ?12, p.type = ?13  where p.id = ?14")
    void saveProductInfoById(String goodTitle, String producer, Integer year, String country, Integer price, String type, Long id);
}
