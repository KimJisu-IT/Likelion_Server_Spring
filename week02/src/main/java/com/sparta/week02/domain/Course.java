package com.sparta.week02.domain;

import com.sparta.week02.models.CourseRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Entity;
import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Course extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String tutor;
    public Course(String title, String tutor) {
        this.title = title;
        this.tutor = tutor;
    }
    public void update(CourseRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.tutor = requestDto.getTutor();
    }

    public Long getId() {
        return this.id;
    }

    @MappedSuperclass // 상속했을 때, 컬럼으로 인식하게 합니다.
    @EntityListeners(AuditingEntityListener.class) // 생성/수정 시간을 자동으로 반영하도록 설정
    public class Timestamped {
        @CreatedDate // 생성일자임을 나타냅니다.
        private LocalDateTime createdAt;
        @LastModifiedDate // 마지막 수정일자임을 나타냅니다.
        private LocalDateTime modifiedAt;
    }

}


