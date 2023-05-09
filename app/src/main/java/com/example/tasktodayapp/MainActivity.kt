package com.example.tasktodayapp



import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tasktodayapp.model.Tarefa.Tarefa
import com.example.tasktodayapp.ui.theme.TaskTodayAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreenContent()
        }
    }
}

@Composable
fun MainScreenContent() {
    val scaffoldState = rememberScaffoldState()
    var scope = rememberCoroutineScope()
    //var tabIndex by remember { mutableStateOf(0) }
    Scaffold(
        scaffoldState = scaffoldState,

        topBar = {
            TopAppBar(

                title = { Text(text = "TaskTodayApp")},
                navigationIcon = {
                    IconButton(onClick = {
                        scope.launch{
                            scaffoldState.drawerState.open()
                        }
                    } ){
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Drawer Menu",
                            tint = Color(0xFF000000),

                            )
                    }
                },

                backgroundColor = Color(0xFFac3939),

                )


        },

        drawerBackgroundColor = Color(0xFFac3939),
        drawerGesturesEnabled = scaffoldState.drawerState.isOpen,

        drawerContent = {
            Box(
                modifier = Modifier
                    .height(16.dp)
            ){
                Text(text = "Opções!!!")
            }

            Column(){
                Text(text = "Opção de Tarefas")
                Text(text = "Opção de Notificações")
                Text(text = "Opção X")
            }


        },

        content = {

                paddingValues -> Log.i("paddinVales", "$paddingValues")
            Column(
                modifier = Modifier
                    .background(Color(0xFFfffff ))
                    .fillMaxSize()
            ) {
                MySearchField(modificador = Modifier.fillMaxWidth())

                //val calendar = Calendar.getInstance()
                //listOf<Tarefa>(Tarefa("Estudar Prova de Calculo", "Cap do livro xyz", Date(), calendar.set{2023}))

                val tProvaDeCalculo = Tarefa(
                    "Estudar Prova de Calculo",
                    "Cap 1 do livro xyz",
                    Date(),
                    Date(),
                    status = 0.0,
                    "#ff6666"
                )

                val tPassearcomcachorro = Tarefa(
                    "ir passear com o cachorro no parque",
                    "as 10:00",
                    Date(),
                    Date(),
                    status = 0.0,
                    "#ff6666"
                )

                val tIrnomercado = Tarefa(
                    "Ir no supermercado",
                    "ir na parte da tarde",
                    Date(),
                    Date(),
                    status = 0.0,
                    "#ff6666"
                )

                var minhaListaDeTarefas = listOf<Tarefa>(tProvaDeCalculo, tPassearcomcachorro, tIrnomercado)
                MyTaskWidgetList(minhaListaDeTarefas)

            }
        },

        bottomBar = {

            BottomAppBar(
                content = {Text("BottomBar")},
                backgroundColor = Color(0xFFac3939 )
            )



        },

        isFloatingActionButtonDocked = false,
        floatingActionButton = { ExtendedFloatingActionButton(
            icon = {
                Icon(
                    imageVector = Icons.Default.AddCircle,
                    contentDescription = "Add Task",
                    tint = Color(0xFFeeeeeeeee)
                )
            },

            text = { Text(text = "ADD", color = Color(0xFFFeeeeeee))},
            onClick = {},
            backgroundColor = Color(0xFFac3939)
        )}

    )

}

@Composable
fun MyTaskWidgetList(listaDeTarefas: List<Tarefa>){
    listaDeTarefas.forEach(
        action = { MyTaskWidget(modificador = Modifier.fillMaxWidth(), tarefaASerMostrada = it)}
    )
}

@Composable
fun MySearchField(modificador: Modifier){
    TextField(
        value = "",
        onValueChange = {},
        modifier = modificador,
        placeholder = { Text(text = "Pesquisar tarefas")},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon",
                tint = Color(0xFF000000),
            )
        }
    )
}


@Composable
fun MyTaskWidget(
    modificador: Modifier,
    tarefaASerMostrada: Tarefa
){
    val dateFormatter = SimpleDateFormat("EEE, MMM dd, yyyy", Locale.getDefault())
    Row(modifier = modificador) {

            Icon(
                imageVector = Icons.Default.Notifications,
                tint = Color(0xFF000000),
                contentDescription = "Icons  of a pendent taks"
            )

            Text(
                text = dateFormatter.format(tarefaASerMostrada.pzoFinal),
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal
            )


        Column(
            modifier = modificador
                .border(width = 1.dp, color = Color.Black)
                .padding(3.dp)
                .background(Color(android.graphics.Color.parseColor(tarefaASerMostrada.cor)))

        ) {
            Text(
                text = tarefaASerMostrada.nome,
                fontSize = 12.sp,
                color = Color(0xFFeeeeeee),
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic
            )


            Text(
                text = tarefaASerMostrada.detalhes.toString(),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                color = Color(0xFFeeeeeee)
            )

        }
    }
    Spacer(modifier = Modifier.height(16.dp))
}


//@Preview(showBackground = true)

