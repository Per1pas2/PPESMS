<?php
$conn = mysqli_connect("192.168.1.94","root","root","homesweethome");

$Pseudo = $_GET['pseudo'];
$Mdp = $_GET['mdp'];
$sql = "SELECT u.Id_Role_USER, u.Id_USER, si.Nom_SOCIETE FROM user as u INNER JOIN societe_intervenant as si ON u.Id_USER = si.id_user WHERE u.Pseudo_USER = '$Pseudo' AND u.Mdp_USER = '$Mdp'";

$raw = mysqli_query($conn,$sql);

while($res=mysqli_fetch_assoc($raw))
{
    $data[]=$res;
}
print(json_encode($data))
?>