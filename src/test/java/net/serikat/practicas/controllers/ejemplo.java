/*
 * package net.izfe.g220.hiesilicieservicioslib.facades.impl;
 * 
 * import org.junit.jupiter.api.BeforeAll; import
 * org.junit.jupiter.api.BeforeEach; import org.junit.jupiter.api.Test; import
 * org.junit.jupiter.api.extension.ExtendWith; import org.mockito.InjectMocks;
 * import org.mockito.Mock; import org.mockito.Mockito; import org.mockito.Spy;
 * import org.mockito.junit.jupiter.MockitoExtension; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.http.HttpStatus; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.test.context.ActiveProfiles; import
 * org.springframework.test.context.ContextConfiguration; import
 * org.springframework.test.context.junit.jupiter.SpringExtension; import
 * org.springframework.web.client.RestTemplate;
 * 
 * import net.izfe.g220.hiesilicieservicioslib.SilicieServiciosConfigTest;
 * import net.izfe.g220.hiesilicieservicioslib.beans.EjercicioContable; import
 * net.izfe.g220.hiesilicieservicioslib.beans.EjercicioRestBean; import
 * net.izfe.g220.hiesilicieservicioslib.config.PropertyConsumer; import
 * net.izfe.g220.hiesilicieservicioslib.facades.EjercicioContableFacade; import
 * net.izfe.g610.swsvariablesentorno.VariablesEntorno;
 * 
 * @ExtendWith(SpringExtension.class)
 * 
 * @ExtendWith(MockitoExtension.class)
 * 
 * @ContextConfiguration(classes = { SilicieServiciosConfigTest.class })
 * 
 * @ActiveProfiles(VariablesEntorno.ENTORNO_TEST) class
 * EjercicioContableFacadeImplTest {
 * 
 * @Autowired
 * 
 * @Spy private PropertyConsumer propertyConsumer;
 * 
 * @InjectMocks private EjercicioContableFacade facade = new
 * EjercicioContableFacadeImpl();
 * 
 * @Mock private RestTemplate restTemplate;
 * 
 * @BeforeAll static void setUpBeforeClass() throws Exception {}
 * 
 * private EjercicioRestBean mockRestBean;
 * 
 * @BeforeEach void setUp() throws Exception { mockRestBean = new
 * EjercicioRestBean(); mockRestBean.setCorrecto(Boolean.TRUE); final
 * ResponseEntity<EjercicioRestBean> mockResponseEntity = new
 * ResponseEntity<EjercicioRestBean>(mockRestBean, HttpStatus.ACCEPTED);
 * Mockito.when(restTemplate.postForEntity(Mockito.anyString(),
 * Mockito.any(Object.class), Mockito.eq(EjercicioRestBean.class)))
 * .thenReturn(mockResponseEntity); }
 * 
 * @Test void testAltaEjercicio() { final EjercicioContable ejercicioContable =
 * new EjercicioContable(); final EjercicioRestBean restBean =
 * facade.altaEjercicio(ejercicioContable); }
 * 
 * 
 * }
 */