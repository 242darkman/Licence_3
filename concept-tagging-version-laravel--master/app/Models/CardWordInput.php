<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class CardWordInput extends Model
{
    use HasFactory;
    protected  $fillable = ['card_id','word'];
}
