/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fernanda
 */
@Entity
@Table(name = "FAQ")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Faq.findAll", query = "SELECT f FROM Faq f"),
    @NamedQuery(name = "Faq.findByIdFaq", query = "SELECT f FROM Faq f WHERE f.idFaq = :idFaq"),
    @NamedQuery(name = "Faq.findByTitulo", query = "SELECT f FROM Faq f WHERE f.titulo = :titulo"),
    @NamedQuery(name = "Faq.findByContenido", query = "SELECT f FROM Faq f WHERE f.contenido = :contenido")})
public class Faq implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_FAQ")
    private Integer idFaq;
    @Size(max = 255)
    @Column(name = "TITULO")
    private String titulo;
    @Size(max = 1024)
    @Column(name = "CONTENIDO")
    private String contenido;
    @Lob
    @Column(name = "IMAGEN")
    private byte[] imagen;

    public Faq() {
    }

    public Faq(Integer idFaq) {
        this.idFaq = idFaq;
    }

    public Integer getIdFaq() {
        return idFaq;
    }

    public void setIdFaq(Integer idFaq) {
        this.idFaq = idFaq;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFaq != null ? idFaq.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Faq)) {
            return false;
        }
        Faq other = (Faq) object;
        if ((this.idFaq == null && other.idFaq != null) || (this.idFaq != null && !this.idFaq.equals(other.idFaq))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Faq[ idFaq=" + idFaq + " ]";
    }
    
}
