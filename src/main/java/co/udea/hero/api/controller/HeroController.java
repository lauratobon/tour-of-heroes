package co.udea.hero.api.controller;

import co.udea.hero.api.model.Hero;
import co.udea.hero.api.service.HeroService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/heroes")
public class HeroController {

    private final Logger log = LoggerFactory.getLogger(HeroController.class);

    private HeroService heroService;

    public HeroController(HeroService heroService) {
        this.heroService = heroService;
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Busca un hero por su id",  response = Hero.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Hero encontrado existosamente"),
            @ApiResponse(code = 400, message = "La petición es invalida"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
    public ResponseEntity<Hero> getHero(@PathVariable Integer id){
        log.info("Rest request buscar heroe por id: "+ id);
        return ResponseEntity.ok(heroService.getHero(id));
    }

    @PostMapping(value= "crear",
    consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Crea un hero con su id",  response = Hero.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Hero creado existosamente"),
            @ApiResponse(code = 400, message = "La petición es invalida"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
    public void addHero(@RequestBody Hero hero){
        heroService.addHero(hero);
    }

    @GetMapping("")
    @ApiOperation(value = "Buscar todos los heroes",  response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Heroes encontrado existosamente"),
            @ApiResponse(code = 400, message = "La petición es invalida"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
   public ResponseEntity<List<Hero>> getHeroes(){
        log.info("Rest request buscar heroes");
        return ResponseEntity.ok(heroService.getHeroes());
    }

    @GetMapping("buscar")
    @ApiOperation(value = "Busca un heroe por un parametro string",
            response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Heroe encontrado existosamente"),
            @ApiResponse(code = 400, message = "La petición es invalida"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
    public ResponseEntity<List<Hero>> searchHeroes(@PathParam("term") String term){
        log.info("Rest request buscar heroes por parámetro: "+ term);
        return ResponseEntity.ok(heroService.searchHeroes(term));
    }

    @GetMapping("consultar404")
    @ApiOperation(value = "Busca un heroe por un parametro entero, sino lo " +
            "encuentra responde undefined",
            response = Hero.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Heroe encontrado existosamente"),
            @ApiResponse(code = 400, message = "La petición es invalida"),
            @ApiResponse(code = 404, message = "undefined"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
    public ResponseEntity<Hero> getHeroNo404(@PathParam("id") Integer id){
        log.info("Rest request buscar heroes por id: "+ id);
        return ResponseEntity.ok(heroService.getHeroNo404(id));
    }
}