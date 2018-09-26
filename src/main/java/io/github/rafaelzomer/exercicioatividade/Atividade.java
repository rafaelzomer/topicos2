package io.github.rafaelzomer.exercicioatividade;

import java.util.Date;
import java.util.List;

public class Atividade {
  Integer codigo;
  String descricao;
  Date dataCadastro;
  Date dataConclusao;
  Integer estagio;
  List<Nota> notas;

  public Atividade() {
  }

  public Atividade(Integer codigo) {
    this.codigo = codigo;
  }

  public static Atividade of(Integer codigo, String descricao, Date dataCadastro, Date dataConclusao, Integer estagio, List<Nota> notas) {
    Atividade atividade = new Atividade();
    atividade.codigo = codigo;
    atividade.descricao = descricao;
    atividade.dataCadastro = dataCadastro;
    atividade.dataConclusao = dataConclusao;
    atividade.estagio = estagio;
    atividade.notas = notas;
    return atividade;
  }

  public Integer getCodigo() {
    return codigo;
  }

  public void setCodigo(Integer codigo) {
    this.codigo = codigo;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public Date getDataCadastro() {
    return dataCadastro;
  }

  public void setDataCadastro(Date dataCadastro) {
    this.dataCadastro = dataCadastro;
  }

  public Date getDataConclusao() {
    return dataConclusao;
  }

  public void setDataConclusao(Date dataConclusao) {
    this.dataConclusao = dataConclusao;
  }

  public Integer getEstagio() {
    return estagio;
  }

  public void setEstagio(Integer estagio) {
    this.estagio = estagio;
  }

  public List<Nota> getNotas() {
    return notas;
  }

  public void setNotas(List<Nota> notas) {
    this.notas = notas;
  }
}
