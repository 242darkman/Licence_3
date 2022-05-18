<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class CardSerie extends Model
{
    use HasFactory;

    protected $fillable = [
        'serie_name',
        'best_score'
    ];
}


