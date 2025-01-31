package br.edu.ifpb.pweb2.cdi.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.pweb2.cdi.controller.AlunoController;
import br.edu.ifpb.pweb2.cdi.model.Aluno;

@Named(value = "cadastroAlunoBean")
@ViewScoped
public class CadastroDeAlunoBean extends GenericBean implements Serializable {
private static final long serialVersionUID = 1L;
	
	@Inject
	private Aluno aluno;
	
	@Inject
	private AlunoController controllerAluno;

	
	@PostConstruct
	public void init() {
		Aluno alunoFlash = (Aluno) this.getFlash("aluno");
		if (alunoFlash != null) {
			this.aluno = alunoFlash;
		}
	}
	
	public String cadastrar() {
		// Usa o dao para inserir o aluno
		Integer id = aluno.getId();
		controllerAluno.saveOrUpdate(aluno);
		
		this.KeepMessages();
		if (id == null) {
			this.addInfoMessage("Aluno cadatrado com sucesso!");
		} else {
			this.addInfoMessage("Aluno atualizado com sucesso!");
		}
		
		// Limpa objeto do formulário
		aluno = new Aluno();
		
		// Retorna para mesma página
		return "consulta?faces-redirect=true";
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	
}

