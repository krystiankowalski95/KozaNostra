package pl.lodz.p.it.ssbd2020.ssbd05.entities.mok;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "password_history", schema = "ssbd05schema", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"password"})
})
@TableGenerator(name = "PasswordHistoryIdGen", table = "id_generator", schema = "ssbd05schema", pkColumnName = "class_name", valueColumnName = "id_range", pkColumnValue = "password_history")
@NamedQueries({
    @NamedQuery(name = "PreviousPassword.findAll", query = "SELECT p FROM PreviousPassword p"),
    @NamedQuery(name = "PreviousPassword.findById", query = "SELECT p FROM PreviousPassword p WHERE p.id = :id"),
    @NamedQuery(name = "PreviousPassword.findByPassword", query = "SELECT p FROM PreviousPassword p WHERE p.password = :password"),
    @NamedQuery(name = "PreviousPassword.findByVersion", query = "SELECT p FROM PreviousPassword p WHERE p.version = :version")})
public class PreviousPassword implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "PasswordHistoryIdGen")
    @NotNull
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "password", nullable = false, length = 64, unique = true)
    private String password;

    @Getter(lombok.AccessLevel.NONE)
    @Setter(lombok.AccessLevel.NONE)
    @Basic(optional = false)
    @Version
    @NotNull
    @Column(name = "version", nullable = false, columnDefinition = "bigint default 1")
    private long version;

    @NotNull
    @JoinColumn(name = "account_id", referencedColumnName = "id", nullable = false, updatable = false)
    @ManyToOne(optional = false)
    private Account account;

    public PreviousPassword() {
    }

    public PreviousPassword(Long id) {
        this.id = id;
    }

    public PreviousPassword(Long id, String password, long version) {
        this.id = id;
        this.password = password;
        this.version = version;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PreviousPassword)) {
            return false;
        }
        PreviousPassword other = (PreviousPassword) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.lodz.p.it.ssbd2020.ssbd05.entities.mok.PreviousPassword[ id=" + id + " ]";
    }
    
}