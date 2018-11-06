package br.com.aluguel.web.rest;

import br.com.aluguel.AluguelCarroApp;

import br.com.aluguel.domain.Aluguel;
import br.com.aluguel.repository.AluguelRepository;
import br.com.aluguel.service.AluguelService;
import br.com.aluguel.service.dto.AluguelDTO;
import br.com.aluguel.service.mapper.AluguelMapper;
import br.com.aluguel.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;


import static br.com.aluguel.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the AluguelResource REST controller.
 *
 * @see AluguelResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AluguelCarroApp.class)
public class AluguelResourceIntTest {

    private static final LocalDate DEFAULT_DATA_RETIRADA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA_RETIRADA = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATA_PREV_DEVOLUCAO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA_PREV_DEVOLUCAO = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATA_REAL_DEVOLUCAO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA_REAL_DEVOLUCAO = LocalDate.now(ZoneId.systemDefault());

    private static final BigDecimal DEFAULT_VALOR_LOCACAO = new BigDecimal(1);
    private static final BigDecimal UPDATED_VALOR_LOCACAO = new BigDecimal(2);

    @Autowired
    private AluguelRepository aluguelRepository;


    @Autowired
    private AluguelMapper aluguelMapper;
    

    @Autowired
    private AluguelService aluguelService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAluguelMockMvc;

    private Aluguel aluguel;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AluguelResource aluguelResource = new AluguelResource(aluguelService);
        this.restAluguelMockMvc = MockMvcBuilders.standaloneSetup(aluguelResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Aluguel createEntity(EntityManager em) {
        Aluguel aluguel = new Aluguel()
            .data_retirada(DEFAULT_DATA_RETIRADA)
            .data_prev_devolucao(DEFAULT_DATA_PREV_DEVOLUCAO)
            .data_real_devolucao(DEFAULT_DATA_REAL_DEVOLUCAO)
            .valor_locacao(DEFAULT_VALOR_LOCACAO);
        return aluguel;
    }

    @Before
    public void initTest() {
        aluguel = createEntity(em);
    }

    @Test
    @Transactional
    public void createAluguel() throws Exception {
        int databaseSizeBeforeCreate = aluguelRepository.findAll().size();

        // Create the Aluguel
        AluguelDTO aluguelDTO = aluguelMapper.toDto(aluguel);
        restAluguelMockMvc.perform(post("/api/aluguels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(aluguelDTO)))
            .andExpect(status().isCreated());

        // Validate the Aluguel in the database
        List<Aluguel> aluguelList = aluguelRepository.findAll();
        assertThat(aluguelList).hasSize(databaseSizeBeforeCreate + 1);
        Aluguel testAluguel = aluguelList.get(aluguelList.size() - 1);
        assertThat(testAluguel.getData_retirada()).isEqualTo(DEFAULT_DATA_RETIRADA);
        assertThat(testAluguel.getData_prev_devolucao()).isEqualTo(DEFAULT_DATA_PREV_DEVOLUCAO);
        assertThat(testAluguel.getData_real_devolucao()).isEqualTo(DEFAULT_DATA_REAL_DEVOLUCAO);
        assertThat(testAluguel.getValor_locacao()).isEqualTo(DEFAULT_VALOR_LOCACAO);
    }

    @Test
    @Transactional
    public void createAluguelWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = aluguelRepository.findAll().size();

        // Create the Aluguel with an existing ID
        aluguel.setId(1L);
        AluguelDTO aluguelDTO = aluguelMapper.toDto(aluguel);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAluguelMockMvc.perform(post("/api/aluguels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(aluguelDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Aluguel in the database
        List<Aluguel> aluguelList = aluguelRepository.findAll();
        assertThat(aluguelList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkData_retiradaIsRequired() throws Exception {
        int databaseSizeBeforeTest = aluguelRepository.findAll().size();
        // set the field null
        aluguel.setData_retirada(null);

        // Create the Aluguel, which fails.
        AluguelDTO aluguelDTO = aluguelMapper.toDto(aluguel);

        restAluguelMockMvc.perform(post("/api/aluguels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(aluguelDTO)))
            .andExpect(status().isBadRequest());

        List<Aluguel> aluguelList = aluguelRepository.findAll();
        assertThat(aluguelList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkData_prev_devolucaoIsRequired() throws Exception {
        int databaseSizeBeforeTest = aluguelRepository.findAll().size();
        // set the field null
        aluguel.setData_prev_devolucao(null);

        // Create the Aluguel, which fails.
        AluguelDTO aluguelDTO = aluguelMapper.toDto(aluguel);

        restAluguelMockMvc.perform(post("/api/aluguels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(aluguelDTO)))
            .andExpect(status().isBadRequest());

        List<Aluguel> aluguelList = aluguelRepository.findAll();
        assertThat(aluguelList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkData_real_devolucaoIsRequired() throws Exception {
        int databaseSizeBeforeTest = aluguelRepository.findAll().size();
        // set the field null
        aluguel.setData_real_devolucao(null);

        // Create the Aluguel, which fails.
        AluguelDTO aluguelDTO = aluguelMapper.toDto(aluguel);

        restAluguelMockMvc.perform(post("/api/aluguels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(aluguelDTO)))
            .andExpect(status().isBadRequest());

        List<Aluguel> aluguelList = aluguelRepository.findAll();
        assertThat(aluguelList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAluguels() throws Exception {
        // Initialize the database
        aluguelRepository.saveAndFlush(aluguel);

        // Get all the aluguelList
        restAluguelMockMvc.perform(get("/api/aluguels?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(aluguel.getId().intValue())))
            .andExpect(jsonPath("$.[*].data_retirada").value(hasItem(DEFAULT_DATA_RETIRADA.toString())))
            .andExpect(jsonPath("$.[*].data_prev_devolucao").value(hasItem(DEFAULT_DATA_PREV_DEVOLUCAO.toString())))
            .andExpect(jsonPath("$.[*].data_real_devolucao").value(hasItem(DEFAULT_DATA_REAL_DEVOLUCAO.toString())))
            .andExpect(jsonPath("$.[*].valor_locacao").value(hasItem(DEFAULT_VALOR_LOCACAO.intValue())));
    }
    

    @Test
    @Transactional
    public void getAluguel() throws Exception {
        // Initialize the database
        aluguelRepository.saveAndFlush(aluguel);

        // Get the aluguel
        restAluguelMockMvc.perform(get("/api/aluguels/{id}", aluguel.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(aluguel.getId().intValue()))
            .andExpect(jsonPath("$.data_retirada").value(DEFAULT_DATA_RETIRADA.toString()))
            .andExpect(jsonPath("$.data_prev_devolucao").value(DEFAULT_DATA_PREV_DEVOLUCAO.toString()))
            .andExpect(jsonPath("$.data_real_devolucao").value(DEFAULT_DATA_REAL_DEVOLUCAO.toString()))
            .andExpect(jsonPath("$.valor_locacao").value(DEFAULT_VALOR_LOCACAO.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingAluguel() throws Exception {
        // Get the aluguel
        restAluguelMockMvc.perform(get("/api/aluguels/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAluguel() throws Exception {
        // Initialize the database
        aluguelRepository.saveAndFlush(aluguel);

        int databaseSizeBeforeUpdate = aluguelRepository.findAll().size();

        // Update the aluguel
        Aluguel updatedAluguel = aluguelRepository.findById(aluguel.getId()).get();
        // Disconnect from session so that the updates on updatedAluguel are not directly saved in db
        em.detach(updatedAluguel);
        updatedAluguel
            .data_retirada(UPDATED_DATA_RETIRADA)
            .data_prev_devolucao(UPDATED_DATA_PREV_DEVOLUCAO)
            .data_real_devolucao(UPDATED_DATA_REAL_DEVOLUCAO)
            .valor_locacao(UPDATED_VALOR_LOCACAO);
        AluguelDTO aluguelDTO = aluguelMapper.toDto(updatedAluguel);

        restAluguelMockMvc.perform(put("/api/aluguels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(aluguelDTO)))
            .andExpect(status().isOk());

        // Validate the Aluguel in the database
        List<Aluguel> aluguelList = aluguelRepository.findAll();
        assertThat(aluguelList).hasSize(databaseSizeBeforeUpdate);
        Aluguel testAluguel = aluguelList.get(aluguelList.size() - 1);
        assertThat(testAluguel.getData_retirada()).isEqualTo(UPDATED_DATA_RETIRADA);
        assertThat(testAluguel.getData_prev_devolucao()).isEqualTo(UPDATED_DATA_PREV_DEVOLUCAO);
        assertThat(testAluguel.getData_real_devolucao()).isEqualTo(UPDATED_DATA_REAL_DEVOLUCAO);
        assertThat(testAluguel.getValor_locacao()).isEqualTo(UPDATED_VALOR_LOCACAO);
    }

    @Test
    @Transactional
    public void updateNonExistingAluguel() throws Exception {
        int databaseSizeBeforeUpdate = aluguelRepository.findAll().size();

        // Create the Aluguel
        AluguelDTO aluguelDTO = aluguelMapper.toDto(aluguel);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAluguelMockMvc.perform(put("/api/aluguels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(aluguelDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Aluguel in the database
        List<Aluguel> aluguelList = aluguelRepository.findAll();
        assertThat(aluguelList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAluguel() throws Exception {
        // Initialize the database
        aluguelRepository.saveAndFlush(aluguel);

        int databaseSizeBeforeDelete = aluguelRepository.findAll().size();

        // Get the aluguel
        restAluguelMockMvc.perform(delete("/api/aluguels/{id}", aluguel.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Aluguel> aluguelList = aluguelRepository.findAll();
        assertThat(aluguelList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Aluguel.class);
        Aluguel aluguel1 = new Aluguel();
        aluguel1.setId(1L);
        Aluguel aluguel2 = new Aluguel();
        aluguel2.setId(aluguel1.getId());
        assertThat(aluguel1).isEqualTo(aluguel2);
        aluguel2.setId(2L);
        assertThat(aluguel1).isNotEqualTo(aluguel2);
        aluguel1.setId(null);
        assertThat(aluguel1).isNotEqualTo(aluguel2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AluguelDTO.class);
        AluguelDTO aluguelDTO1 = new AluguelDTO();
        aluguelDTO1.setId(1L);
        AluguelDTO aluguelDTO2 = new AluguelDTO();
        assertThat(aluguelDTO1).isNotEqualTo(aluguelDTO2);
        aluguelDTO2.setId(aluguelDTO1.getId());
        assertThat(aluguelDTO1).isEqualTo(aluguelDTO2);
        aluguelDTO2.setId(2L);
        assertThat(aluguelDTO1).isNotEqualTo(aluguelDTO2);
        aluguelDTO1.setId(null);
        assertThat(aluguelDTO1).isNotEqualTo(aluguelDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(aluguelMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(aluguelMapper.fromId(null)).isNull();
    }
}
