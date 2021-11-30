package com.example.cscs308project;

import com.example.csc308project.GroupController;

import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GroupControllerLoopTests {

    @Test //Loop body isn't executed at all
    public void testParseGroupLoop1() throws IOException {
        File groupinfo1 = new File("data/groupinfo.mla");
        File groupinfo2 = new File("testData/groupinfo.mla");
        Files.deleteIfExists(groupinfo2.toPath());
        Files.copy(groupinfo1.toPath(), groupinfo2.toPath());

        BufferedWriter bw = new BufferedWriter(new FileWriter("data/groupinfo.mla"));
        bw.write(""); //no groups are registered in the system
        bw.close();

        assertEquals(0, GroupController.parseGroup().size()); //group list is empty if the loop body isn't executed at all (no groups registered in system)

        Files.deleteIfExists(groupinfo1.toPath());
        Files.copy(groupinfo2.toPath(), groupinfo1.toPath());
    }

    @Test //Loop body is executed once
    public void testParseGroupLoop2() throws IOException {
        File groupinfo1 = new File("data/groupinfo.mla");
        File groupinfo2 = new File("testData/groupinfo.mla");
        Files.deleteIfExists(groupinfo2.toPath());
        Files.copy(groupinfo1.toPath(), groupinfo2.toPath());

        BufferedWriter bw = new BufferedWriter(new FileWriter("data/groupinfo.mla"));
        bw.write("supervisors admin"); //one group is registered in the system
        bw.close();

        assertEquals(1, GroupController.parseGroup().size()); //group list has only one element if the loop body is executed once

        Files.deleteIfExists(groupinfo1.toPath());
        Files.copy(groupinfo2.toPath(), groupinfo1.toPath());
    }

    @Test //Loop body is executed twice
    public void testParseGroupLoop3() throws IOException {
        File groupinfo1 = new File("data/groupinfo.mla");
        File groupinfo2 = new File("testData/groupinfo.mla");
        Files.deleteIfExists(groupinfo2.toPath());
        Files.copy(groupinfo1.toPath(), groupinfo2.toPath());

        BufferedWriter bw = new BufferedWriter(new FileWriter("data/groupinfo.mla"));
        bw.write("supervisors admin\nsecuritylevel4 bob"); //two groups are registered in the system
        bw.close();

        assertEquals(2, GroupController.parseGroup().size()); //group list has two elements if the loop body is executed twice

        Files.deleteIfExists(groupinfo1.toPath());
        Files.copy(groupinfo2.toPath(), groupinfo1.toPath());
    }

    @Test //Loop body is executed 15 times (A company might typically use this software, in which 15 groups is typical)
    public void testParseGroupLoop4() throws IOException {
        File groupinfo1 = new File("data/groupinfo.mla");
        File groupinfo2 = new File("testData/groupinfo.mla");
        Files.deleteIfExists(groupinfo2.toPath());
        Files.copy(groupinfo1.toPath(), groupinfo2.toPath());

        BufferedWriter bw = new BufferedWriter(new FileWriter("data/groupinfo.mla"));
        bw.write("supervisors admin\nsecuritylevel4 bob\na\nb\nc\nd\ne\nf\ng\nh\ni\nj\nk\nl\nm"); //fifteen groups are registered in the system
        bw.close();

        assertEquals(15, GroupController.parseGroup().size()); //group list has 15 elements if the loop body is executed 15 times

        Files.deleteIfExists(groupinfo1.toPath());
        Files.copy(groupinfo2.toPath(), groupinfo1.toPath());
    }

    @Test //Loop body is executed n-1=4 times (Given n=5, or number of groups in system at present moment)
    public void testParseGroupLoop5() throws IOException {
        File groupinfo1 = new File("data/groupinfo.mla");
        File groupinfo2 = new File("testData/groupinfo.mla");
        Files.deleteIfExists(groupinfo2.toPath());
        Files.copy(groupinfo1.toPath(), groupinfo2.toPath());

        BufferedWriter bw = new BufferedWriter(new FileWriter("data/groupinfo.mla"));
        bw.write("supervisors admin\nsecuritylevel4 bob\na\nb"); //four groups are registered in the system
        bw.close();

        assertEquals(4, GroupController.parseGroup().size()); //group list has 4 elements if the loop body is executed 4 times

        Files.deleteIfExists(groupinfo1.toPath());
        Files.copy(groupinfo2.toPath(), groupinfo1.toPath());
    }

    @Test //Loop body is executed n=5 times (Given n=5, or number of groups in system at present moment)
    public void testParseGroupLoop6() throws IOException {
        File groupinfo1 = new File("data/groupinfo.mla");
        File groupinfo2 = new File("testData/groupinfo.mla");
        Files.deleteIfExists(groupinfo2.toPath());
        Files.copy(groupinfo1.toPath(), groupinfo2.toPath());

        BufferedWriter bw = new BufferedWriter(new FileWriter("data/groupinfo.mla"));
        bw.write("supervisors admin\nsecuritylevel4 bob\na\nb\nc"); //five groups are registered in the system
        bw.close();

        assertEquals(5, GroupController.parseGroup().size()); //group list has 5 elements if the loop body is executed 5 times

        Files.deleteIfExists(groupinfo1.toPath());
        Files.copy(groupinfo2.toPath(), groupinfo1.toPath());
    }

    @Test //Loop body is executed n+1=6 times (Given n=5, or number of groups in system at present moment)
    public void testParseGroupLoop7() throws IOException {
        File groupinfo1 = new File("data/groupinfo.mla");
        File groupinfo2 = new File("testData/groupinfo.mla");
        Files.deleteIfExists(groupinfo2.toPath());
        Files.copy(groupinfo1.toPath(), groupinfo2.toPath());

        BufferedWriter bw = new BufferedWriter(new FileWriter("data/groupinfo.mla"));
        bw.write("supervisors admin\nsecuritylevel4 bob\na\nb\nc\nd"); //six groups are registered in the system
        bw.close();

        assertEquals(6, GroupController.parseGroup().size()); //group list has 6 elements if the loop body is executed 6 times

        Files.deleteIfExists(groupinfo1.toPath());
        Files.copy(groupinfo2.toPath(), groupinfo1.toPath());
    }

}
