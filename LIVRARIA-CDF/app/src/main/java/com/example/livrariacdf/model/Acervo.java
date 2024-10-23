package com.example.livrariacdf.model;

import java.util.ArrayList;

public class Acervo {

    private static Acervo instance = null;

    private ArrayList<Livro> livros;

    private Acervo() {
        this.gerarAcervo();
    }

    public static Acervo getInstance() {
        if (instance == null) {
            instance = new Acervo();
        }
        return instance;
    }




    public Livro getLivroById(int id) {
        Livro livro = null;
        int i = 0;
        for (Livro l : livros) {
            if (l.id == id) return l;
        }
        return null;
    }

    public ArrayList<Livro> getLivros(EnumGenero genero) {
        ArrayList<Livro> selecao = new ArrayList<>();
        for (Livro l : livros) {
            if (l.genero == genero) selecao.add(l);
        }
        return selecao;
    }



    /*
        GERAÇÃO HARD CODED DOS DADOS DE LIVROS (sem implementação de db)
     */
    private void gerarAcervo() {
        int id = 0;
        livros = new ArrayList<>();

        // Gerando livros de Desenvolvimento
        Livro livro = new Livro();
        livro.titulo = "Java: Como Programar";
        livro.autor = "P. Deitel & H. Deitel";
        livro.genero = EnumGenero.DEV;
        livro.id = ++id;
        livro.recursoImg = "java_programar";
        livros.add(livro);

        livro = new Livro();
        livro.titulo = "React: Aprenda Praticando";
        livro.autor = "M. S. Silva";
        livro.genero = EnumGenero.DEV;
        livro.id = ++id;
        livro.recursoImg = "react_praticando";
        livros.add(livro);

        livro = new Livro();
        livro.titulo = "Introdução ao HTML5 e CSS3";
        livro.autor = "R. Clark & O. Studholme & M. Manian";
        livro.genero = EnumGenero.DEV;
        livro.id = ++id;
        livro.recursoImg = "intr_html5_css3";
        livros.add(livro);

        // Gerando livros de ficção científica
        livro = new Livro();
        livro.titulo = "O Problema dos Três Corpos";
        livro.autor = "Cixin Liu";
        livro.genero = EnumGenero.SCI_FI;
        livro.id = ++id;
        livro.recursoImg = "tres_corpos";
        livros.add(livro);

        livro = new Livro();
        livro.titulo = "Nada Mais Será Como Antes";
        livro.autor = "Miguel Nicolelis";
        livro.genero = EnumGenero.SCI_FI;
        livro.id = ++id;
        livro.recursoImg = "nada_mais";
        livros.add(livro);

        livro = new Livro();
        livro.titulo = "O Homem do Castelo Alto";
        livro.autor = "Philip K. Dick";
        livro.genero = EnumGenero.SCI_FI;
        livro.id = ++id;
        livro.recursoImg = "castelo_alto";
        livros.add(livro);

        // Gerando HQs
        livro = new Livro();
        livro.titulo = "O Reino do Amanhã";
        livro.autor = "Mark Waid & Alex Ross";
        livro.genero = EnumGenero.HQ;
        livro.id = ++id;
        livro.recursoImg = "reino_amanha";
        livros.add(livro);

        livro = new Livro();
        livro.titulo = "A Espada Selvagem de Conan";
        livro.autor = "Roy Thomas & John Buscema";
        livro.genero = EnumGenero.HQ;
        livro.id = ++id;
        livro.recursoImg = "espada_conan";
        livros.add(livro);

        livro = new Livro();
        livro.titulo = "Homem de Ferro: Extremis";
        livro.autor = "Adi Granov";
        livro.genero = EnumGenero.HQ;
        livro.id = ++id;
        livro.recursoImg = "homem_ferro_extremis";
        livros.add(livro);

        livro = new Livro();
        livro.titulo = "Loki";
        livro.autor = "Esad Ribic";
        livro.genero = EnumGenero.HQ;
        livro.id = ++id;
        livro.recursoImg = "loki";
        livros.add(livro);

    }
}
