package com.game.sudoku.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.game.sudoku.entity.Sudoku;
import com.game.sudoku.model.DifficultyLevel;
import com.game.sudoku.model.SudokuGrid;
import com.game.sudoku.repository.SudokuRepository;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

/**
 * @author : Ancy Kuruvilla
 */
@RunWith(MockitoJUnitRunner.class)
public class GameServiceImplTest {

    @Mock
    private SudokuRepository sudokuRepository;

    @Mock
    private ValidatorService validator;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private GameServiceImpl gameService;

    @Test
    public void toJsonNullValueTest(){
        Assert.assertThat(gameService.toJson(null), Matchers.isEmptyOrNullString());
    }

    @Test
    public void toJsonValidValueTest() throws JsonProcessingException {
        given(objectMapper.writeValueAsString(any())).willThrow(JsonProcessingException.class);
        Assert.assertThat(gameService.toJson(new Object()), Matchers.nullValue());
    }

    @Test
    public void find() {
        //when
        gameService.find(1);

        //then
        Mockito.verify(sudokuRepository).findById(1);
    }

    @Test
    public void saveValidValueTest() throws JsonProcessingException {
        //given
        SudokuGrid sudokuGrid = mock(SudokuGrid.class);
        given(objectMapper.writeValueAsString(any())).willReturn("Test");

        //when
        gameService.save(sudokuGrid);

        //then
        Mockito.verify(sudokuRepository).addOrUpdate(Mockito.any(Sudoku.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void saveInvalidValueTest() throws JsonProcessingException {
        //given
        SudokuGrid sudokuGrid = mock(SudokuGrid.class);
        given(objectMapper.writeValueAsString(any())).willReturn(null);

        //when
        gameService.save(sudokuGrid);

        //then
        Mockito.verifyZeroInteractions(sudokuRepository);
    }

    @Test
    public void generateSolution(){
        //when
        SudokuGrid sudokuGrid = gameService.generateSolution();
        //then
        Assert.assertThat(sudokuGrid.getSolution(), Matchers.hasSize(9));
        Assert.assertThat(sudokuGrid.getSolution(),
                Matchers.not(Matchers.contains(Matchers.nullValue())));
        for(List<Integer> row:sudokuGrid.getSolution()){
            Assert.assertThat(row,Matchers.not(Matchers.contains(Matchers.nullValue())));
        }
    }

    @Test
    public void createPuzzle(){
        SudokuGrid sudokuGrid = new SudokuGrid();
        List<List<Integer>> grid =  asList(
                asList(2,6,8,1,5,9,3,4,7),
                asList(3,4,7,2,6,8,1,5,9),
                asList(1,5,9,3,4,7,2,6,8),
                asList(8,2,6,9,1,5,7,3,4),
                asList(7,3,4,8,2,6,9,1,5),
                asList(9,1,5,7,3,4,8,2,6),
                asList(6,8,2,5,9,1,4,7,3),
                asList(4,7,3,6,8,2,5,9,1),
                asList(5,9,1,4,7,3,6,8,2));
        sudokuGrid.setSolution(grid);
        //when
        sudokuGrid = gameService.caretePuzzle(sudokuGrid, DifficultyLevel.HARD);
        //then
        Assert.assertThat(sudokuGrid.getPuzzle(),Matchers.hasSize(9));
    }
}
