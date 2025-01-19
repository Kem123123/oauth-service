@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsService userDetailsServiceImpl;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(UserDetailsService userDetailsServiceImpl, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // Allow access to Swagger UI and API docs
                        .requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**", "/webjars/**").permitAll()
                        // Protect the rest of the API
                        .anyRequest().authenticated())
                .csrf(AbstractHttpConfigurer::disable)  // Disable CSRF for stateless API
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));  // Stateless session management

        // Add JWT filter before UsernamePasswordAuthenticationFilter
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsServiceImpl);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
}
