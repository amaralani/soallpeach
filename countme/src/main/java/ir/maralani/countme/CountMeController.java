package ir.maralani.countme;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CountMeController {
    private int count = 0;

    @PostMapping
    public ResponseEntity addNumberToCount(HttpEntity<byte[]> requestEntity) {
        count += Integer.parseInt(StringUtils.trimAllWhitespace(new String(requestEntity.getBody())));
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public ResponseEntity getCount() {
        return ResponseEntity.ok(count);
    }
}
