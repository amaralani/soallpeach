package ir.maralani.countme;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class CountMeController {
    private int count = 0;

    @PostMapping(value = "/")
    @ResponseBody
    public ResponseEntity addNumberToCount(@RequestParam MultiValueMap<String, String> body) {
        body.keySet().forEach(key -> count += Integer.parseInt(key));
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public ResponseEntity getCount() {
        return ResponseEntity.ok(count);
    }
}
