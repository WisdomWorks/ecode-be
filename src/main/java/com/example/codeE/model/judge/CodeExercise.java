package com.example.codeE.model.judge;

import com.example.codeE.model.exercise.Exercise;
import com.example.codeE.model.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class CodeExercise extends Exercise {
//    @NotBlank(message = "Code is required")
//    private String code;
    private String description;

    //    @ManyToMany
//    private List<User> authors;
//    @ManyToMany
//    private List<Profile> curators;
//    @ManyToMany
//    private List<Profile> testers;
//    @ManyToMany
//    private List<ProblemType> types;
//    @ManyToMany
//    private List<ProblemGroup> groups;
    @NotNull(message = "Time limit is required")
    private Float timeLimit;
    @NotNull(message = "Memory limit is required")
    private Integer memoryLimit;
    private boolean shortCircuit;
    @NotNull(message = "Points is required")
    private Float points;
    private boolean partial;

    @ManyToMany
    private List<Language> allowedLanguages;

    private boolean isPublic;

//    private boolean isManuallyManaged;

    private LocalDateTime date;

//    @ManyToMany
//    private List<User> bannedUsers;

    private String ogImage;

    private String summary;
//    private Integer userCount;
    private Float acRate;
    private boolean isFullMarkup;
    private String submissionSourceVisibilityMode;
//    private boolean isOrganizationPrivate;

}
