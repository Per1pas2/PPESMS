<?php
$conn = mysqli_connect("192.168.1.94","root","root","homesweethome");

$sql = "SELECT Localisation_BIEN_IMMOBILIER FROM bien_immobilier";

$raw = mysqli_query($conn,$sql);

while($res=mysqli_fetch_assoc($raw))
{
    $data[]=$res;
}
print(json_encode($data))
?>