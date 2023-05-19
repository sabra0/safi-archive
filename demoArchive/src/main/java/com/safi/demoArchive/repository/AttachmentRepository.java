package com.safi.demoArchive.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.safi.demoArchive.entities.Attachment;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Long> {

}
