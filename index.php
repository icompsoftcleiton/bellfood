<?php

class BellFoods
{

	function __construct()
    {
        if (method_exists($this, $_REQUEST['action'])) {
            call_user_func(array(
                $this,
                $_REQUEST['action']
            ));
        }
    }

    public function buscar()
    {
		$query = $_REQUEST['query'];

		$dados = array();

		$conexao = mysql_connect("localhost", "icomp934_sistema", "icompsoft");
		mysql_select_db("icomp934_bellfoods", $conexao);

		$query = mysql_query("select * from pratos where nome like '%$query%' or descricao like '%$query%' order by curtidas desc");
		while ($row = mysql_fetch_assoc($query)) {
			$estabelecimento = $row['estabelecimento'];
			$estabelecimento = mysql_fetch_assoc(mysql_query("select * from estabelecimento where id = $estabelecimento"));
			$row['estabelecimento'] = $estabelecimento;
			$dados[] = $row;
		}

		echo json_encode($dados);
	}

	public function curtir()
	{
		$prato = $_REQUEST['prato'];
		$conexao = mysql_connect("localhost", "icomp934_sistema", "icompsoft");
		mysql_select_db("icomp934_bellfoods", $conexao);

		mysql_query("update pratos set curtidas = curtidas + 1 where id = $prato");
	}

	public function descurtir()
	{
		$prato = $_REQUEST['prato'];
		$conexao = mysql_connect("localhost", "icomp934_sistema", "icompsoft");
		mysql_select_db("icomp934_bellfoods", $conexao);

		mysql_query("update pratos set curtidas = curtidas - 1 where id = $prato");
	}

}
new BellFoods();

?>