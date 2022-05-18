<?php

namespace App\Http\Controllers;

use App\Models\Card;
use App\Models\CardSerie;
use Illuminate\Http\Request;
use Inertia\Inertia;

class ManageController extends Controller
{
    public function index($id)
    {

        $serie      = CardSerie::find($id);                 //On recupère la serie
        $all_cards  = Card::where('serie_id',$id)->get();   //Les cartes qui appartiennet à la série


        return Inertia::render('Manage/Manage',[
            'serie'     => $serie,
            'all_cards' => $all_cards
        ]);

    }


    public function delete_serie($id)
    {
        CardSerie::where('id',$id)->delete();

        return redirect('dashboard');
    }

    public function delete_card($id)
    {
        Card::where('id',$id)->delete();
        return back();

    }

}
