package ru.otus.domain;

import jakarta.annotation.Nonnull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@NoArgsConstructor
@Table("address")
public class Address{

    @Id
    private Long clientId;

    @Nonnull
    private String street;

    @Override
    public String toString() {
        return "Address{" + "id=" + clientId + ", street='" + street + '\'' + '}';
    }
}
