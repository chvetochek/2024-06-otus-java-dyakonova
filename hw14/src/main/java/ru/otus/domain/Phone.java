package ru.otus.domain;

import jakarta.annotation.Nonnull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@NoArgsConstructor
@Table("phone")
public class Phone {
    @Id
    @Column("id")
    private Long id;

    @Nonnull
    @Column("number")
    private String number;

    @PersistenceCreator
    public Phone(Long id, @Nonnull String number) {
        this.id = id;
        this.number = number;
    }

    @Override
    public String toString() {
        return "Phone{" + "id=" + id + ", number='" + number + '\'' + '}';
    }
}