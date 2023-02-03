package com.oodles.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oodles.domain.EmailNotification;

public interface MailnotificationRepository extends JpaRepository<EmailNotification,Long>{

}
