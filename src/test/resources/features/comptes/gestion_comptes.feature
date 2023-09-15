Feature: Gestion des comptes

  Scénario: Créer un nouveau compte
  Given un utilisateur existe
  When l'utilisateur crée un nouveau compte
  Then un nouveau compte est créé pour l'utilisateur

  Scénario: Mettre à jour le solde du compte
  Given un utilisateur existe
  And un compte existe pour l'utilisateur avec un solde initial
  When l'utilisateur met à jour le solde du compte
  Then le solde du compte est mis à jour correctement
