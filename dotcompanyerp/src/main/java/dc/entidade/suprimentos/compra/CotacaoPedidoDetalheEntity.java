package dc.entidade.suprimentos.compra;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import dc.anotacoes.Caption;
import dc.entidade.framework.AbstractMultiEmpresaModel;
import dc.entidade.framework.ComboCode;
import dc.entidade.framework.ComboValue;

@Entity
@Table(name = "compra_cotacao_pedido_detalhe")
@XmlRootElement
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class CotacaoPedidoDetalheEntity extends
		AbstractMultiEmpresaModel<Integer> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "compra_cotacao_pedido_detalhe_id_seq")
	@SequenceGenerator(name = "compra_cotacao_pedido_detalhe_id_seq", sequenceName = "compra_cotacao_pedido_detalhe_id_seq", allocationSize = 1, initialValue = 0)
	@Basic(optional = false)
	@ComboCode
	@Analyzer(definition = "dc_combo_analyzer")
	private Integer id;

	@Field
	@Caption("Quantidade pedida")
	@Column(name = "quantidade_pedida")
	@ComboValue
	@Analyzer(definition = "dc_combo_analyzer")
	private BigDecimal quantidadePedida = new BigDecimal(0);

	/**
	 * REFERENCIA - FK
	 */

	@Caption("Cotação - Detalhe")
	@ManyToOne
	@JoinColumn(name = "id_compra_cotacao_detalhe")
	private CotacaoDetalheEntity cotacaoDetalhe;

	@Caption("Pedido")
	@ManyToOne
	@JoinColumn(name = "id_compra_pedido")
	private PedidoEntity pedido;

	/**
	 * REFERENCIA - LIST
	 */

	/**
	 * TRANSIENT
	 */

	/**
	 * CONSTRUTOR
	 */

	public CotacaoPedidoDetalheEntity() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * GETS AND SETS
	 */

	@Override
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getQuantidadePedida() {
		return quantidadePedida;
	}

	public void setQuantidadePedida(BigDecimal quantidadePedida) {
		this.quantidadePedida = (quantidadePedida == null ? new BigDecimal(0)
				: quantidadePedida);
	}

	public CotacaoDetalheEntity getCotacaoDetalhe() {
		return cotacaoDetalhe;
	}

	public void setCotacaoDetalhe(CotacaoDetalheEntity cotacaoDetalhe) {
		this.cotacaoDetalhe = cotacaoDetalhe;
	}

	public PedidoEntity getPedido() {
		return pedido;
	}

	public void setPedido(PedidoEntity pedido) {
		this.pedido = pedido;
	}

	/**
	 * TO STRING
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}