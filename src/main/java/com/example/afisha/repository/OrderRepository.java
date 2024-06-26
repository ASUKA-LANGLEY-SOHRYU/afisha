package com.example.afisha.repository;

import com.example.afisha.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o FROM Order o WHERE o.user.id = :userId")
    List<Order> findOrdersByUserId(Long userId);

    @Query("select SUM(o.count) from Order o where o.event.id = :id")
    Integer getNumberOfOrdersByEventId(Long id);

    List<Order> findByEventId(Long id);
}
