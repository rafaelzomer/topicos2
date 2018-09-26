package io.github.rafaelzomer.exercicioatividade;

import java.util.Date;

public class Nota {
  private Integer codigo;
  private String nota;
  private Date dataCadastro;

  public static Nota of(Integer codigo, String nota, Date dataCadastro) {
    Nota entity = new Nota();
    entity.codigo = codigo;
    entity.nota = nota;
    entity.dataCadastro = dataCadastro;
    return entity;
  }

  public Integer getCodigo() {
    return codigo;
  }

  public void setCodigo(Integer codigo) {
    this.codigo = codigo;
  }

  public String getNota() {
    return nota;
  }

  public void setNota(String nota) {
    this.nota = nota;
  }

  public Date getDataCadastro() {
    return dataCadastro;
  }

  public void setDataCadastro(Date dataCadastro) {
    this.dataCadastro = dataCadastro;
  }
}
