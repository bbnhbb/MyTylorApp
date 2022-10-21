package com.mytylor.app.repository;

import com.mytylor.app.entity.Dress;
import com.mytylor.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DressRepository extends JpaRepository<Dress, Long> {
}
