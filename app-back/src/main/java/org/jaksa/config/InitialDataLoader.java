package org.jaksa.config;

import lombok.RequiredArgsConstructor;
import org.jaksa.dtos.command.CreateSessionCommand;
import org.jaksa.dtos.query.CompanyDto;
import org.jaksa.mappers.CompanyMapper;
import org.jaksa.models.CompanyModel;
import org.jaksa.repositories.CompanyRepository;
import org.jaksa.repositories.SessionRepository;
import org.jaksa.services.interfaces.command.CreateSessionService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InitialDataLoader {
    private final CompanyRepository companyRepository;
    private final SessionRepository sessionRepository;
    private final CreateSessionService service;
    private final CompanyMapper mapper;

    @PostConstruct
    public void uploadData() {
        List<CompanyModel> companiesList = companyRepository.findAll();
        if (companiesList.isEmpty()) {
            companiesList.add(companyRepository.save(new CompanyModel("aapl", "APPLE")));
            companiesList.add(companyRepository.save(new CompanyModel("amzn", "AMAZON")));
            companiesList.add(companyRepository.save(new CompanyModel("tsla", "TESLA")));
            companiesList.add(companyRepository.save(new CompanyModel("wmt", "WALMART")));
            companiesList.add(companyRepository.save(new CompanyModel("nsrgy", "NESTLE")));
            companiesList.add(companyRepository.save(new CompanyModel("xom", "EXXON")));
            companiesList.add(companyRepository.save(new CompanyModel("csco", "CISCO")));
            companiesList.add(companyRepository.save(new CompanyModel("gs", "GOLDMAN-SACHS")));
            companiesList.add(companyRepository.save(new CompanyModel("bac", "BANK-OF-AMERICA")));
            companiesList.add(companyRepository.save(new CompanyModel("f", "FORD")));
            companiesList.add(companyRepository.save(new CompanyModel("gme", "GAMESTOP")));
            companiesList.add(companyRepository.save(new CompanyModel("fb", "META/FACEBOOK")));
            companiesList.add(companyRepository.save(new CompanyModel("jpm", "JPMORGAN")));
        }

        List<CompanyDto> companyDtos = mapper.modelsToDtos(companiesList);

        if (sessionRepository.findAll().isEmpty()) {
            service.initialCreate(new CreateSessionCommand("Dot-com bubble", "Enter the 2000 with the biggest tech bubble in history caused by excessive speculation of Internet-related companies in the late 1990s, a period of massive growth in the use and adoption of the Internet! The value of equity markets grew 5 times  between 1995 and 2000. Equities' prices crashed after the bubble burst in 2001 causing several Web companies to go bust.","Wejd?? w rok 2000 z najwi??ksz?? ba??k?? technologiczn?? w historii, spowodowan?? nadmiernymi spekulacjami firm zwi??zanych z Internetem w p????nych latach 90., okresie ogromnego wzrostu wykorzystania i adopcji Internetu! Warto???? rynk??w akcji wzros??a pi??ciokrotnie w latach 1995-2000. Ceny akcji za??ama??y si?? po p??kni??ciu ba??ki w 2001 r., co spowodowa??o upadek kilku firm internetowych.", "2000-01-03", "2001-03-30", companyDtos.stream().filter(c -> c.getAbbreviation().equals("csco")).toList(), 10000L));
            service.initialCreate(new CreateSessionCommand("The global crash of 2008", "Enter the crash that caused the Great Recession that can be still felt today and defined whole generations and economies! After the housing market bubble burst, all economies suffered greatly and the government bailouts were used. The crisis sparked the Great Recession which resulted in almost all economies in the World taking damage.", "Wejd?? do krachu, kt??ry spowodowa?? Wielk?? Recesj??, kt??ra jest nadal odczuwalna i zdefiniowa??a ca??e pokolenia i gospodarki! Po p??kni??ciu ba??ki na rynku mieszkaniowym wszystkie gospodarki bardzo ucierpia??y i wykorzystano rz??dowe pakiety ratunkowe. Kryzys wywo??a?? Wielk?? Recesj??, w wyniku kt??rej prawie wszystkie gospodarki na ??wiecie zosta??y zniszczone ", "2008-09-01", "2008-12-31", companyDtos.stream().filter(c -> c.getAbbreviation().equals("gs") || c.getAbbreviation().equals("bac") || c.getAbbreviation().equals("xom") || c.getAbbreviation().equals("jpm")).toList(), 10000L));
            service.initialCreate(new CreateSessionCommand("The automotive industry crisis", "See a stock crash that defined the whole car industry and made SUV unpopular! This part of the financial crisis of 2007???2008 and the resulting Great Recession. The crisis affected European, Asian and American automobile manufacturers because of lack of potential clients for new cars. During it consumers turned to smaller, cheaper, more fuel-efficient vehicles from Japan and Europe", "Zobacz krach gie??dowy, kt??ry zdefiniowa?? ca??y przemys?? samochodowy i sprawi??, ??e SUV sta?? si?? niepopularny! Ta cz?????? kryzysu finansowego lat 2007???2008 i wynikaj??ca z niego Wielka Recesja. Kryzys dotkn???? europejskich, azjatyckich i ameryka??skich producent??w samochod??w z powodu braku potencjalnych klient??w na nowe samochody. W tym czasie konsumenci z Japonii i Europy zwr??cili si?? w stron?? mniejszych, ta??szych i bardziej paliwooszcz??dnych pojazd??w ", "2008-09-01", "2009-02-27", companyDtos.stream().filter(c -> c.getAbbreviation().equals("f")).toList(), 10000L));
            service.initialCreate(new CreateSessionCommand("Start of COViD-19", "Check how the global pandemic shapes and slows down different industries! This global economic recession caused by the COVID-19 pandemic began in February 2020.  After a year of fighting the virus, the COVID-19 lockdowns and other means taken in early 2020 drove the global economy into crisis.", "Sprawd??, jak globalna pandemia kszta??tuje i spowalnia r????ne bran??e! Ta globalna recesja gospodarcza spowodowana pandemi?? COVID-19 rozpocz????a si?? w lutym 2020 r. Po roku walki z wirusem blokada COVID-19 i inne ??rodki podj??te na pocz??tku 2020 r. doprowadzi??y ??wiatow?? gospodark?? do kryzysu. ", "2020-02-19", "2020-03-31", companyDtos.stream().filter(c -> c.getAbbreviation().equals("jpm") || c.getAbbreviation().equals("xom") || c.getAbbreviation().equals("f") || c.getAbbreviation().equals("nsrgy") || c.getAbbreviation().equals("fb") || c.getAbbreviation().equals("gs")).toList(), 10000L));
            service.initialCreate(new CreateSessionCommand("GameStop squeeze", "See how individual investors in the Internet successfully fought large hedge funds! The social media users artificially increased the price of GAMESTOP in very short time to combat big investing funds by a tool called a short squeeze which hurts the stock's short sellers.", "Zobacz, jak indywidualni inwestorzy w Internecie skutecznie walczyli z du??ymi funduszami hedgingowymi! U??ytkownicy medi??w spo??eczno??ciowych sztucznie podnie??li cen?? GAMESTOP w bardzo kr??tkim czasie, aby walczy?? z du??ymi funduszami inwestycyjnymi za pomoc?? narz??dzia zwanego short squeeze, kt??re szkodzi short sellerom akcji. ", "2021-01-21", "2021-02-04", companyDtos.stream().filter(c -> c.getAbbreviation().equals("gme")).toList(), 10000L));
            service.initialCreate(new CreateSessionCommand("Facebook/Meta Metaverse crash", "Enjoy one of the worst one day stock crashes in history caused by unpopular investments and weaker-than-expected earnings growth expected! The app also showed the first quarterly decline in daily active users on record.", "Ciesz si?? jednym z najgorszych jednodniowych krach??w gie??dowych w historii spowodowanych niepopularnymi inwestycjami i s??abszym ni?? oczekiwano wzrostem zysk??w! Aplikacja wykaza??a r??wnie?? pierwszy kwartalny spadek liczby aktywnych u??ytkownik??w dziennie. ", "2022-02-01", "2022-02-14", companyDtos.stream().filter(c -> c.getAbbreviation().equals("fb")).toList(), 10000L));
            service.initialCreate(new CreateSessionCommand("Tesla and Musk's tweets", "See how CEO's popularity and actions can influence the companies they own. This celebrity said he was selling almost all his possessions and won't own a house, and that his company's stock's price was too high. Guess how that influenced the behavior of the stocks.", "Zobacz, jak popularno???? i dzia??ania prezesa mog?? wp??yn???? na firmy, kt??rych s?? w??a??cicielami. Ten celebryta powiedzia??, ??e sprzedaje prawie ca??y sw??j dobytek i nie b??dzie w??a??cicielem domu, a cena akcji jego firmy jest zbyt wysoka. Zgadnij, jak wp??yn????o to na zachowanie akcji ", "2021-10-01", "2021-12-20", companyDtos.stream().filter(c -> c.getAbbreviation().equals("tsla")).toList(), 10000L));
        }
    }
}
