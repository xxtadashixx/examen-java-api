#!/bin/bash
# Script de test pour l'API de gestion de tickets

BASE_URL="http://localhost:8080"

echo "1. Créer un nouveau ticket:"
curl -X POST "${BASE_URL}/ticket"
echo -e "\n---------------------------------\n"

echo "2. Voir l'état de la file d'attente:"
curl "${BASE_URL}/etat"
echo -e "\n---------------------------------\n"

echo "3. Appeler le prochain ticket à la caisse 1:"
curl -X POST "${BASE_URL}/caisse/1"
echo -e "\n---------------------------------\n"

echo "4. Appeler le prochain ticket à la caisse 2:"
curl -X POST "${BASE_URL}/caisse/2"
echo -e "\n---------------------------------\n"

echo "5. Appeler le prochain ticket à la caisse 3:"
curl -X POST "${BASE_URL}/caisse/3"
echo -e "\n---------------------------------\n"

echo "6. Vérifier l'état mis à jour:"
curl "${BASE_URL}/etat"
echo -e "\n---------------------------------\n"

echo "Test complet terminé"