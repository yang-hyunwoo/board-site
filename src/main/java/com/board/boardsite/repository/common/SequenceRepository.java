package com.board.boardsite.repository.common;

import com.board.boardsite.domain.common.SequenceId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SequenceRepository extends JpaRepository<SequenceId, Long> {
}
