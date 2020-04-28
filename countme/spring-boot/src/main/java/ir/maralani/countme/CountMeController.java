package ir.maralani.countme;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CountMeController {
    private List<String> list = new ArrayList<>();

    @PostMapping
    public ResponseEntity addNumberToCount(HttpEntity<byte[]> requestEntity) {
        add(new String(requestEntity.getBody()));
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public ResponseEntity getCount() {
        return ResponseEntity.ok(list.stream().mapToLong(value -> Integer.parseInt(value)).sum());
    }

    @RequestMapping(value = "/reset", method = RequestMethod.GET)
    public ResponseEntity resetCount() {
        list.clear();
        return ResponseEntity.ok().build();
    }

    private synchronized void add(String value) {
        list.add(StringUtils.trimAllWhitespace(value));
    }
}
