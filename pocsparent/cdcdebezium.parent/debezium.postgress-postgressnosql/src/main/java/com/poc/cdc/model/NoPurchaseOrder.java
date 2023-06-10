package com.poc.cdc.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.annotations.Type;

import com.poc.cdc.service.HstoreUserType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@TypeDef(name = "hstore", typeClass = HstoreUserType.class)
public class NoPurchaseOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nopurchase_order_ids")
	@SequenceGenerator(name = "nopurchase_order_ids", sequenceName = "seq_purchase_order", allocationSize = 50)
	private Long id;

	private long customerId;

	private LocalDateTime orderDate;

	//@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "purchaseOrder")
	//private List<OrderLine> lineItems;

	@Type(type = "hstore")
    @Column(columnDefinition = "hstore")
	private Map<String, String> lineItems = new HashMap<String, String>();
	
	public BigDecimal getTotalValue() {
        return lineItems.stream().map(OrderLine::getTotalPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
