package com.example.trainbooking.repository;
import com.example.trainbooking.entity.CancelTicket;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CancelTicketRepository extends JpaRepository<CancelTicket, Long> {
}
