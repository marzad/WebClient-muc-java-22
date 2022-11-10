package de.neuefische.marzad.webclientmucjava22;
/*
import de.neuefische.marzad.webclientmucjava22.NewMessage;
import de.neuefische.marzad.webclientmucjava22.Repo;
import de.neuefische.marzad.webclientmucjava22.SuccessClass;*/
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/api")
public class WebClientController {
    @GetMapping("do-something")
    public SuccessClass doSomething () {
        Repo repos = WebClient
                .builder()
                .baseUrl("https://eoq2vuf7lltn3qi.m.pipedream.net")
                .build()
                .method(HttpMethod.GET)
                .uri("/1")
                .exchangeToMono(
                        clientResponse -> clientResponse.bodyToMono(Repo.class)
                ).block();

        NewMessage newMessage = new NewMessage(2, repos.animal() + " " + repos.description());


        SuccessClass success = WebClient
                .builder()
                .baseUrl("https://eolhzflzeqdbtg3.m.pipedream.net")
                .build()
                .method(HttpMethod.POST)
                .uri("")
                .bodyValue(newMessage)
                .exchangeToMono(
                        clientResponse -> clientResponse.bodyToMono(SuccessClass.class)
                ).block();
        return success;
    }
}