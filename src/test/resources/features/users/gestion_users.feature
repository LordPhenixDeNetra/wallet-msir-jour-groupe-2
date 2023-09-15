Feature: Gestion des utilisateurs

  Scénario: Créer un nouvel utilisateur
  Given un utilisateur existe avec le nom "Alpha"
  When l'utilisateur crée un nouvel utilisateur
  Then le nouvel utilisateur est enregistré dans le système

  Scénario: Mettre à jour les informations de l'utilisateur
  Given un utilisateur existe avec le nom "Alpha"
  When l'utilisateur met à jour les informations de l'utilisateur
  Then les informations de l'utilisateur sont mises à jour
