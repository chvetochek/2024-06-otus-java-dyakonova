package ru.otus.domain;

import jakarta.annotation.Nonnull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Table("client")
public class Client implements Cloneable {

    @Id
    @Column("id")
    private Long id;

    @Nonnull
    private String name;

    private Long addressId;

    @MappedCollection(idColumn = "client_id")
    private List<Phone> phones;

    public Client(String name) {
        this.id = null;
        this.name = name;
    }

    public Client(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Client(Long id, String name, Long addressId, List<Phone> phones) {
        this.id = id;
        this.name = name;
        this.addressId = addressId;
        this.phones = phones;
    }

    @Override
    @SuppressWarnings({"java:S2975", "java:S1182"})
    public Client clone() {
        return new Client(this.id, this.name, this.addressId, this.phones);
    }

    @Override
    public String toString() {
        return "Client{" + "id=" + id + ", name='" + name + '\'' + ", addressId='" + addressId + '\'' + ", phones=" + phones + '}';
    }
}