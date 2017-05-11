package dc.visao.framework.geral;

import java.util.HashSet;
import java.util.Set;

import dc.entidade.relatorio.Relatorio;

public class RelatorioTree {
	private Set<RelatorioTree> filhos = new HashSet<>();;
	private Relatorio relatorio;

	public RelatorioTree() {
	}

	public RelatorioTree searchNodeByRelatorio(Relatorio child) {
		if (filhos.size() > 0) {
			for (RelatorioTree filho : filhos) {
				if (filho.getRelatorio().equals(child)) {
					return filho;
				} else {
					return filho.searchNodeByRelatorio(child);
				}
			}
		}
		return null;
	}

	public RelatorioTree(Relatorio relatorio) {
		this.relatorio = relatorio;
	}

	public boolean isLeaf() {
		return filhos.isEmpty();
	}

	public boolean isRoot() {
		return relatorio == null;
	}

	public Set<RelatorioTree> getFilhos() {
		return filhos;
	}

	public void addChild(RelatorioTree tree) {
		filhos.add(tree);
	}

	public Relatorio getRelatorio() {
		return relatorio;
	}

	public void setRelatorio(Relatorio relatorio) {
		this.relatorio = relatorio;
	}

}
