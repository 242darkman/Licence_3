<?php

namespace App\Http\Controllers;

use App\Models\Card;
use App\Models\CardSerie;
use App\Models\Serie;
use App\Models\User;
use Illuminate\Http\Request;
use Inertia\Inertia;

class GameController extends Controller
{

    public function index($id)
    {

        $value      = CardSerie::find($id);
        $all_cards  = Card::where('serie_id',$id)->get();


        if($value != null && $all_cards != null) {
            return Inertia::render('Game/Game', [
                "all_cards" => $all_cards->shuffle()
            ]);
        }else{
            return redirect()->route('dashboard');
        }

    }



    public function update_score(Request $request)
    {

        auth()->id();
        $user = User::find(auth()->id());
        $score_final = $request['score'] + $user->user_score;

        $user->update(['user_score' => $score_final]);

        return redirect()->route('dashboard');

    }

}
