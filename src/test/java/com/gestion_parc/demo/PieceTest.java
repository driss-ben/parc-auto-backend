package com.gestion_parc.demo;


import com.gestion_parc.demo.beans.Piece;
import com.gestion_parc.demo.services.implementations.PieceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Optional;

@SpringBootTest
public class PieceTest {

    @Autowired
    PieceService pieceService;

    @Test
    public void add(){
        Piece piece1 = Piece.builder()
                .nom("Trous 4x4")
                .cout(2.5)
                .quantiteStock(100L)
                .build();
        Piece piece2 = Piece.builder()
                .nom("Trous 2x2")
                .cout(1.5)
                .quantiteStock(300L)
                .build();
        pieceService.add(piece1);
        //pieceService.create(piece2);
    }
    @Test
    public void findByNom(){
        Piece piece = pieceService.findByNom("trous 4x4");
        System.out.println(piece);
    }

    @Test
    public void findById(){
        Piece piece = pieceService.findById(1l);
            System.out.println(piece);
    }

    @Test
    public void deleteById(){
        pieceService.deleteById(1l);
    }
    @Test
    public void delete(){
        Piece piece = pieceService.findById(1l);
    }

    @Test
    public void update(){
        Piece piece = pieceService.findByNom("Trous 4x4");
    }

}
