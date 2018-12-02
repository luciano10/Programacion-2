package cinepago.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A Pago.
 */
@Entity
@Table(name = "pago")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Pago implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "importe", precision = 10, scale = 2, nullable = false)
    private BigDecimal importe;

    @NotNull
    @Size(min = 36, max = 36)
    @Column(name = "pago_uuid", length = 36, nullable = false)
    private String pagoUuid;

    @NotNull
    @Column(name = "created", nullable = false)
    private ZonedDateTime created;

    @NotNull
    @Column(name = "updated", nullable = false)
    private ZonedDateTime updated;

    @ManyToOne
    @JsonIgnoreProperties("pagos")
    private Tarjeta tarjeta;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public Pago importe(BigDecimal importe) {
        this.importe = importe;
        return this;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public String getPagoUuid() {
        return pagoUuid;
    }

    public Pago pagoUuid(String pagoUuid) {
        this.pagoUuid = pagoUuid;
        return this;
    }

    public void setPagoUuid(String pagoUuid) {
        this.pagoUuid = pagoUuid;
    }

    public ZonedDateTime getCreated() {
        return created;
    }

    public Pago created(ZonedDateTime created) {
        this.created = created;
        return this;
    }

    public void setCreated(ZonedDateTime created) {
        this.created = created;
    }

    public ZonedDateTime getUpdated() {
        return updated;
    }

    public Pago updated(ZonedDateTime updated) {
        this.updated = updated;
        return this;
    }

    public void setUpdated(ZonedDateTime updated) {
        this.updated = updated;
    }

    public Tarjeta getTarjeta() {
        return tarjeta;
    }

    public Pago tarjeta(Tarjeta tarjeta) {
        this.tarjeta = tarjeta;
        return this;
    }

    public void setTarjeta(Tarjeta tarjeta) {
        this.tarjeta = tarjeta;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pago pago = (Pago) o;
        if (pago.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pago.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Pago{" +
            "id=" + getId() +
            ", importe=" + getImporte() +
            ", pagoUuid='" + getPagoUuid() + "'" +
            ", created='" + getCreated() + "'" +
            ", updated='" + getUpdated() + "'" +
            "}";
    }
}
