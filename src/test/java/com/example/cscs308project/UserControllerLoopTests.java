package com.example.cscs308project;

import com.example.csc308project.UserController;

import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserControllerLoopTests {
    @Test //Loop body isn't executed at all
    public void testGetAllUsersLoop1() throws IOException {
        File userinfo1 = new File("data/userinfo.mla");
        File userinfo2 = new File("testData/userinfo.mla");
        Files.deleteIfExists(userinfo2.toPath());
        Files.copy(userinfo1.toPath(), userinfo2.toPath());

        BufferedWriter bw = new BufferedWriter(new FileWriter("data/userinfo.mla"));
        bw.write(""); //no users are registered in the system
        bw.close();

        assertEquals(0, UserController.getAllUsers().size()); //userNameList is empty if the loop body isn't executed at all (no users registered in system)

        Files.deleteIfExists(userinfo1.toPath());
        Files.copy(userinfo2.toPath(), userinfo1.toPath());
    }

    @Test //Loop body is executed once
    public void testGetAllUsersLoop2() throws IOException {
        File userinfo1 = new File("data/userinfo.mla");
        File userinfo2 = new File("testData/userinfo.mla");
        Files.deleteIfExists(userinfo2.toPath());
        Files.copy(userinfo1.toPath(), userinfo2.toPath());

        BufferedWriter bw = new BufferedWriter(new FileWriter("data/userinfo.mla"));
        bw.write("admin supervisors\n1234"); //one user registered
        bw.close();

        assertEquals(1, UserController.getAllUsers().size()); //userNameList has only one user if the loop body is executed once

        Files.deleteIfExists(userinfo1.toPath());
        Files.copy(userinfo2.toPath(), userinfo1.toPath());
    }

    @Test //Loop body is executed twice
    public void testGetAllUsersLoop3() throws IOException {
        File userinfo1 = new File("data/userinfo.mla");
        File userinfo2 = new File("testData/userinfo.mla");
        Files.deleteIfExists(userinfo2.toPath());
        Files.copy(userinfo1.toPath(), userinfo2.toPath());

        BufferedWriter bw = new BufferedWriter(new FileWriter("data/userinfo.mla"));
        bw.write("admin supervisors\n1234\nbob securitylevel4\nthebuilder"); //two users registered
        bw.close();

        assertEquals(2, UserController.getAllUsers().size()); //userNameList has two users if the loop body is executed twice

        Files.deleteIfExists(userinfo1.toPath());
        Files.copy(userinfo2.toPath(), userinfo1.toPath());
    }

    @Test //Loop body is executed 50 times (typically, there will be MANY users (a company, perhaps) registered in the system)
    public void testGetAllUsersLoop4() throws IOException {
        File userinfo1 = new File("data/userinfo.mla");
        File userinfo2 = new File("testData/userinfo.mla");
        Files.deleteIfExists(userinfo2.toPath());
        Files.copy(userinfo1.toPath(), userinfo2.toPath());

        BufferedWriter bw = new BufferedWriter(new FileWriter("data/userinfo.mla"));
        bw.write("a\n1\nb\n2\nc\n3\nd\n4\ne\n5\nf\n6\ng\n7\nh\n8\ni\n9\nj\n10\nk\n11\nl\n12\nm\n13\nn\n14\no\n15\np\n16\n" +
                "q\n17\nr\n18\ns\n19\nt\n20\nu\n21\nv\n22\nw\n23\nx\n24\ny\n25\nz\n26\naa\n27\nab\n28\nac\n29\nad\n30\nae\n31\n" +
                "af\n32\nag\n33\nah\n34\nai\n35\naj\n36\nak\n37\nal\n38\nam\n39\nan\n40\nao\n41\nap\n42\naq\n43\nar\n44\nas\n45\n" +
                "at\n46\nau\n47\nav\n48\naw\n49\nax\n50"); //fifty users registered
        bw.close();

        assertEquals(50, UserController.getAllUsers().size()); //userNameList has fifty users if the loop body is executed fifty times

        Files.deleteIfExists(userinfo1.toPath());
        Files.copy(userinfo2.toPath(), userinfo1.toPath());
    }

    @Test //Loop body is executed n-1=3 times (Given n=4, or the number of users in the system at the moment)
    public void testGetAllUsersLoop5() throws IOException {
        File userinfo1 = new File("data/userinfo.mla");
        File userinfo2 = new File("testData/userinfo.mla");
        Files.deleteIfExists(userinfo2.toPath());
        Files.copy(userinfo1.toPath(), userinfo2.toPath());

        BufferedWriter bw = new BufferedWriter(new FileWriter("data/userinfo.mla"));
        bw.write("a\n1\nb\n2\nc\n3"); //3 users registered
        bw.close();

        assertEquals(3, UserController.getAllUsers().size()); //userNameList has 3 users if the loop body is executed 3 times

        Files.deleteIfExists(userinfo1.toPath());
        Files.copy(userinfo2.toPath(), userinfo1.toPath());
    }

    @Test //Loop body is executed n=4 times (Given n=4, or the number of users in the system at the moment)
    public void testGetAllUsersLoop6() throws IOException {
        File userinfo1 = new File("data/userinfo.mla");
        File userinfo2 = new File("testData/userinfo.mla");
        Files.deleteIfExists(userinfo2.toPath());
        Files.copy(userinfo1.toPath(), userinfo2.toPath());

        BufferedWriter bw = new BufferedWriter(new FileWriter("data/userinfo.mla"));
        bw.write("a\n1\nb\n2\nc\n3\nd\n4"); //4 users registered
        bw.close();

        assertEquals(4, UserController.getAllUsers().size()); //userNameList has 4 users if the loop body is executed 4 times

        Files.deleteIfExists(userinfo1.toPath());
        Files.copy(userinfo2.toPath(), userinfo1.toPath());
    }

    @Test //Loop body is executed n+1=5 times (Given n=4, or the number of users in the system at the moment)
    public void testGetAllUsersLoop7() throws IOException {
        File userinfo1 = new File("data/userinfo.mla");
        File userinfo2 = new File("testData/userinfo.mla");
        Files.deleteIfExists(userinfo2.toPath());
        Files.copy(userinfo1.toPath(), userinfo2.toPath());

        BufferedWriter bw = new BufferedWriter(new FileWriter("data/userinfo.mla"));
        bw.write("a\n1\nb\n2\nc\n3\nd\n4\ne\n5"); //five users registered
        bw.close();

        assertEquals(5, UserController.getAllUsers().size()); //userNameList has five users if the loop body is executed five times

        Files.deleteIfExists(userinfo1.toPath());
        Files.copy(userinfo2.toPath(), userinfo1.toPath());
    }
}
