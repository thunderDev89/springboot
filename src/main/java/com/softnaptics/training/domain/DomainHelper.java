package com.softnaptics.training.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Audrik ! on 24/05/2017.
 */
public class DomainHelper {
    public static List<Task> createTasks() {
        List<Task> tasks = new ArrayList<>(5);
        tasks.add(new Task(
                "Speak stage à Magishan",
                "Demander à Magishan s'il ne recherche pas de stagiaire actuellement. C'est pour le mbindi de Youbi.",
                Task.PRIORITY_LOW,
                Date.from(LocalDateTime.of(2017, 05, 27, 13, 00).atZone(ZoneId.systemDefault()).toInstant()),
                Date.from(LocalDateTime.of(2017, 05, 27, 17, 00).atZone(ZoneId.systemDefault()).toInstant())

        ));
        tasks.add(new Task(
                "M2",
                "Aller se coucher tôt parce que M2 demain",
                Task.PRIORITY_HIGH,
                Date.from(LocalDateTime.of(2017, 05, 24, 13, 00).atZone(ZoneId.systemDefault()).toInstant()),
                Date.from(LocalDateTime.of(2017, 05, 25, 02, 00).atZone(ZoneId.systemDefault()).toInstant())
        ));
        tasks.add(new Task(
                "Souhaiter HBD",
                "Souhaiter joyeux anniversaire à Audrik",
                Task.PRIORITY_MEDIUM,
                Date.from(LocalDateTime.of(LocalDate.now(), LocalTime.MAX).atZone(ZoneId.systemDefault()).toInstant())
        ));
        tasks.add(new Task(
                "Remplir fiche d'imposition",
                "Remplir en ligne la fiche d'imposition et l'envoyer en ligne",
                Task.PRIORITY_HIGH,
                Date.from(LocalDateTime.of(2017, 05, 30, 23, 59).atZone(ZoneId.systemDefault()).toInstant())
        ));
        tasks.add(new Task(
                "Rdv avec Cynthia",
                "Aller au restaurant avec Cynthia. Le restaurant se trouve au : 1 place de la mairie, 92220 Montrouge",
                Task.PRIORITY_LOW,
                Date.from(LocalDateTime.of(2017, 05, 27, 13, 00).atZone(ZoneId.systemDefault()).toInstant()),
                Date.from(LocalDateTime.of(2017, 05, 27, 17, 00).atZone(ZoneId.systemDefault()).toInstant())

        ));
        return tasks;
    }
}
