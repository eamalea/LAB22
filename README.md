# JNIDemo – Intégration C++ natif dans Android avec JNI

Application Android de démonstration de l’interface **JNI** (Java Native Interface).  
Elle illustre :
- le chargement d’une bibliothèque partagée (`libnative-lib.so`)
- l’appel de fonctions C++ depuis Java
- le passage de paramètres (entier, chaîne, tableau)
- la gestion d’erreurs (overflow, paramètres invalides)
- les logs natifs dans Logcat

## Fonctionnalités

1. **Hello JNI** : retourne une simple chaîne depuis le code natif.
2. **Factoriel** : calcule `n!` en C++ avec contrôle de dépassement (`INT_MAX`).
3. **Inversion de chaîne** : inverse une chaîne Java en C++.
4. **Somme de tableau** : calcule la somme d’un tableau d’entiers passé depuis Java.

## Prérequis

- Android Studio (dernière version stable)
- NDK, CMake, LLDB installés (via SDK Manager)
- SDK minimum API 24 (Android 7.0)

## Installation

1. Clonez ce dépôt.
2. Ouvrez le projet dans Android Studio.
3. Synchronisez Gradle.
4. Lancez l’application sur un émulateur ou un téléphone (API 24+).

## Structure
