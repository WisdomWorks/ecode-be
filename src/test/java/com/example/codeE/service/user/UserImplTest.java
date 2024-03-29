package com.example.codeE.service.user;

//@SpringBootTest
//@AutoConfigureMockMvc
//@ExtendWith(MockitoExtension.class)
class UserImplTest {
//    @InjectMocks
//    private UserImpl userImplement;
//    @Mock
//    private UserRepository userRepository;
//    private List<User> mockDataUser;
//
//    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//    LocalDateTime dateTime = LocalDateTime.parse("2024-01-28 05:10:52", formatter);
//    @BeforeEach
//    void setUp() {
//        this.mockDataUser = new ArrayList<>();
//        this.mockDataUser.add(new User("usr1", "user", "user@gmail.com", "username", "Teacher", dateTime, dateTime));
//        this.mockDataUser.add(new User("usr2", "user", "user@gmail.com", "username", "Teacher", dateTime, dateTime));
//        this.mockDataUser.add(new User("usr3", "user", "user@gmail.com", "username", "Teacher", dateTime, dateTime));
//        this.mockDataUser.add(new User("usr4", "user", "user@gmail.com", "username", "Teacher", dateTime, dateTime));
//        this.mockDataUser.add(new User("usr5", "user", "user@gmail.com", "username", "Teacher", dateTime, dateTime));
//    }
//
//    @AfterEach
//    void tearDown() {
//    }
//
//    @Test
//    void getAllUsers() {
//        when(userRepository.findAll()).thenReturn(mockDataUser);
//        assertEquals(mockDataUser, userImplement.getAll());
//    }
//
//    @Test
//    void getUsersByRoleAndSearchKeyword() {
//        when(userRepository.findUsersByRoleAndSearchKeyword(Mockito.any(), Mockito.any())).thenReturn(mockDataUser);
//        assertEquals(mockDataUser, userImplement
//                .getUsersByRoleAndSearchKeyword(new GetUsersRequest("Teacher", "name", 1, 2)));
//    }
//
//    @Test
//    void paginateUsers() {
//        when(userRepository
//                .findUsersByRoleAndSearchKeywordWithPagination(Mockito.any(), Mockito.any(), Mockito.any()))
//                .thenReturn(mockDataUser);
//        assertEquals(mockDataUser, userImplement.paginateUsers(new GetUsersRequest("Teacher", "name", 1, 2)));
//    }
}