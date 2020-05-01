package pl.lodz.p.it.ssbd2020.ssbd05.entities.mok;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "account_login_data", schema = "ssbd05schema", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"login"})
})
@SecondaryTables({
        @SecondaryTable(name = "authentication_data", schema = "ssbd05schema"),
        @SecondaryTable(name = "account_personal_data", schema = "ssbd05schema", uniqueConstraints = {
                @UniqueConstraint(columnNames = {"email"})
        })
})
@TableGenerator(name = "AccountIdGen", table = "id_generator", schema = "ssbd05schema", pkColumnName = "class_name", valueColumnName = "id_range", pkColumnValue = "account_login_data")
@NamedQueries({
        @NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a"),
        @NamedQuery(name = "Account.findById", query = "SELECT a FROM Account a WHERE a.id = :id"),
        @NamedQuery(name = "Account.findByLogin", query = "SELECT a FROM Account a WHERE a.login = :login"),
        @NamedQuery(name = "Account.findByActive", query = "SELECT a FROM Account a WHERE a.active = :active"),
        @NamedQuery(name = "Account.findByConfirmed", query = "SELECT a FROM Account a WHERE a.confirmed = :confirmed"),
        @NamedQuery(name = "Account.findByFirstname", query = "SELECT a FROM Account a WHERE a.firstname = :firstname"),
        @NamedQuery(name = "Account.findByLastname", query = "SELECT a FROM Account a WHERE a.lastname = :lastname"),
        @NamedQuery(name = "Account.findByEmail", query = "SELECT a FROM Account a WHERE a.email = :email"),
        @NamedQuery(name = "Account.findByLastSuccessfulAuth", query = "SELECT a FROM Account a WHERE a.lastSuccessfulAuth = :lastSuccessfulAuth"),
        @NamedQuery(name = "Account.findByLastFailedAuth", query = "SELECT a FROM Account a WHERE a.lastFailedAuth = :lastFailedAuth"),
        @NamedQuery(name = "Account.findByLastAuthIp", query = "SELECT a FROM Account a WHERE a.lastAuthIp = :lastAuthIp"),
        @NamedQuery(name = "Account.findByFailedAuthCounter", query = "SELECT a FROM Account a WHERE a.failedAuthCounter = :failedAuthCounter"),
        @NamedQuery(name = "Account.findByForcePasswordChange", query = "SELECT a FROM Account a WHERE a.forcePasswordChange = :forcePasswordChange"),
        @NamedQuery(name = "Account.findByToken", query = "SELECT a FROM Account a WHERE a.veryficationToken = :token")})
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    @Setter(lombok.AccessLevel.NONE)
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "AccountIdGen")
    @NotNull
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "login", nullable = false, length = 32, unique = true, updatable = false)
    private String login;

    @Basic(optional = false)
    @NotNull
    @Size(min = 64, max = 64)
    @Column(name = "password", nullable = false, length = 64)
    private String password;

    @Basic(optional = false)
    @NotNull
    @Column(name = "active", nullable = false, columnDefinition = "boolean default true")
    private boolean active;

    @Basic(optional = false)
    @NotNull
    @Column(name = "confirmed", nullable = false, columnDefinition = "boolean default false")
    private boolean confirmed;

    @Getter(lombok.AccessLevel.NONE)
    @Setter(lombok.AccessLevel.NONE)
    @Basic(optional = false)
    @Version
    @NotNull
    @Column(name = "version", nullable = false, columnDefinition = "bigint default 1")
    private long version;

    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "account")
    private Collection<AccessLevel> accessLevelCollection = new ArrayList<>();

    @OneToMany(mappedBy = "account")
    private Collection<PreviousPassword> previousPasswordCollection = new ArrayList<>();

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(table = "account_personal_data", name = "firstname", nullable = false, length = 32)
    private String firstname;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(table = "account_personal_data", name = "lastname", nullable = false, length = 32)
    private String lastname;

    @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(table = "account_personal_data", name = "email", nullable = false, length = 32, unique = true, updatable = false)
    private String email;

    @Column(table = "authentication_data", name = "last_successful_auth")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastSuccessfulAuth;

    @Column(table = "authentication_data", name = "last_failed_auth")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastFailedAuth;

    @Size(max = 255)
    @Column(table = "authentication_data", name = "last_auth_ip")
    private String lastAuthIp;

    @Basic(optional = false)
    @NotNull
    @Column(table = "authentication_data", name = "failed_auth_counter", nullable = false, columnDefinition = "integer default 0")
    private int failedAuthCounter;

    @Basic(optional = false)
    @NotNull
    @Column(table = "authentication_data", name = "force_password_change", nullable = false, columnDefinition = "boolean default true")
    private boolean forcePasswordChange;

    @Basic(optional = false)
    @Column(table = "authentication_data", name = "veryfication_token")
    private String veryficationToken;

    @OneToOne(mappedBy = "account")
    private ForgotPasswordToken forgotPasswordToken;

    public Account() {
        this.active = true;
        this.forcePasswordChange = true;
        this.veryficationToken = UUID.randomUUID().toString().replace("-", "");
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Account)) {
            return false;
        }
        Account other = (Account) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "pl.lodz.p.it.ssbd2020.ssbd05.entities.mok.Account[ id=" + id + " ]";
    }
}