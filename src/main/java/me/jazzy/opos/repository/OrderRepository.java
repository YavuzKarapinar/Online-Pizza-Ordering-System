package me.jazzy.opos.repository;

import me.jazzy.opos.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o from Order o where o.user.id = :userId")
    Optional<List<Order>> findByUserId(@Param("userId") Long userId);

    @Query("select o from Order o where o.user.id = :userId and o.id = :id")
    Optional<Order> findSpecificOrderByIds(@Param("userId") Long userId, @Param("id") Long id);
}